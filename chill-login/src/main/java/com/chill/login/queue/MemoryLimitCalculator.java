package com.chill.login.queue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * {@link java.lang.Runtime#freeMemory()} 技术用于通过使用当前最大可用内存的百分比来计算内存限制，该百分比可用于 {@link MemoryLimiter}.
 *
 * @author chill
 * @see MemoryLimiter
 */
public class MemoryLimitCalculator {

    private static volatile long maxAvailable;

    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    static {
        // 加载此类时立即刷新以防止 maxAvailable 为 0
        refresh();
        // 每 50 毫秒检查一次以提高性能
        SCHEDULER.scheduleWithFixedDelay(MemoryLimitCalculator::refresh, 50, 50, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(SCHEDULER::shutdown));
    }

    private static void refresh() {
        maxAvailable = Runtime.getRuntime().freeMemory();
    }

    /**
     * 获取当前 JVM 的最大可用内存
     *
     * @return 最大可用内存
     */
    public static long maxAvailable() {
        return maxAvailable;
    }

    /**
     * 将当前 JVM 的最大可用内存占结果的百分比作为限制
     *
     * @param percentage 百分比
     * @return 可用内存
     */
    public static long calculate(final float percentage) {
        if (percentage <= 0 || percentage > 1) {
            throw new IllegalArgumentException();
        }
        return (long) (maxAvailable() * percentage);
    }

    /**
     * 默认情况下，它占用当前 JVM 最大可用内存的 80%
     *
     * @return 可用内存
     */
    public static long defaultLimit() {
        return (long) (maxAvailable() * 0.8);
    }
}
