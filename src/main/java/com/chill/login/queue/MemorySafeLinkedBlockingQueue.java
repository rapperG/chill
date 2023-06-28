package com.chill.login.queue;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 可以完全解决由{@link java.util.concurrent.LinkedBlockingQueue}引起的OOM问题
 *
 * @author chill
 */
public class MemorySafeLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {

    private static final long serialVersionUID = 8032578371739960142L;

    public static int THE_256_MB = 256 * 1024 * 1024;

    private int maxFreeMemory;

    public MemorySafeLinkedBlockingQueue() {
        this(THE_256_MB);
    }

    public MemorySafeLinkedBlockingQueue(final int maxFreeMemory) {
        super(Integer.MAX_VALUE);
        this.maxFreeMemory = maxFreeMemory;
    }

    public MemorySafeLinkedBlockingQueue(final Collection<? extends E> c,
                                         final int maxFreeMemory) {
        super(c);
        this.maxFreeMemory = maxFreeMemory;
    }

    /**
     * 设置最大可用内存。
     *
     * @param maxFreeMemory 最大可用内存
     */
    public void setMaxFreeMemory(final int maxFreeMemory) {
        this.maxFreeMemory = maxFreeMemory;
    }

    /**
     * 获取最大可用内存
     *
     * @return 最大可用内存
     */
    public int getMaxFreeMemory() {
        return maxFreeMemory;
    }

    /**
     * 确定是否有剩余可用内存。
     *
     * @return 如果具有可用内存，则为 true
     */
    public boolean hasRemainedMemory() {
        return MemoryLimitCalculator.maxAvailable() > maxFreeMemory;
    }

    @Override
    public void put(final E e) throws InterruptedException {
        if (hasRemainedMemory()) {
            super.put(e);
        }
    }

    @Override
    public boolean offer(final E e, final long timeout, final TimeUnit unit) throws InterruptedException {
        return hasRemainedMemory() && super.offer(e, timeout, unit);
    }

    @Override
    public boolean offer(final E e) {
        return hasRemainedMemory() && super.offer(e);
    }
}
