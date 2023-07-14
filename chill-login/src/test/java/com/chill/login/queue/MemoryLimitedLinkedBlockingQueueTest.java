//package com.chill.login.queue;
//
//import net.bytebuddy.agent.ByteBuddyAgent;
//import org.junit.jupiter.api.Test;
//
//import java.lang.instrument.Instrumentation;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public class MemoryLimitedLinkedBlockingQueueTest {
//    @Test
//    public void test() throws Exception {
//        ByteBuddyAgent.install();
//        final Instrumentation instrumentation = ByteBuddyAgent.getInstrumentation();
//        MemoryLimitedLinkedBlockingQueue<Object> queue = new MemoryLimitedLinkedBlockingQueue<>(1, instrumentation);
//        //一个对象需要超过 1 个字节的空间，所以它会在这里失败
//        assertThat(queue.offer(new Object()), is(false));
//
//        //成功
//        queue.setMemoryLimit(Integer.MAX_VALUE);
//        assertThat(queue.offer(new Object()), is(true));
//    }
//}
