package com.chill.login.queue;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.Test;

import java.lang.instrument.Instrumentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author chill
 */
public class MemorySafeLinkedBlockingQueueTest {
    @Test
    public void test() throws Exception {
        ByteBuddyAgent.install();
        final Instrumentation instrumentation = ByteBuddyAgent.getInstrumentation();
        final long objectSize = instrumentation.getObjectSize((Runnable) () -> {
        });
        int maxFreeMemory = (int) MemoryLimitCalculator.maxAvailable();
        MemorySafeLinkedBlockingQueue<Runnable> queue = new MemorySafeLinkedBlockingQueue<>(maxFreeMemory);
        // 所有内存都为 JVM 保留，因此此处将失败
        assertThat(queue.offer(() -> {
        }), is(false));

        // maxFreeMemory-objectSize Byte内存是为JVM保留的，所以这将成功
        queue.setMaxFreeMemory((int) (MemoryLimitCalculator.maxAvailable() - objectSize));
        assertThat(queue.offer(() -> {
        }), is(true));
    }
}
