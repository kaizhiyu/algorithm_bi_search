package com.algorithm.$3_concurrent.pactice.source;

/**
 * $03_01_NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class $03_01_NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready){
                /**
                 A hint to the scheduler that the current thread is willing to yield its current use of a processor.
                 The scheduler is free to ignore this hint.
                 Yield is a heuristic attempt to improve relative progression between threads that would otherwise over-utilise a CPU.
                 Its use should be combined with detailed profiling and benchmarking to ensure that
                 it actually has the desired effect.
                 It is rarely appropriate to use this method. It may be useful for debugging or testing purposes,
                 where it may help to reproduce bugs due to race conditions. It may also be useful when designing concurrency control constructs
                 such as the ones in the java.util.concurrent.locks package.
                 提示调度器当前线程愿意放弃当前处理器的使用。
                   调度程序可以自由地忽略这个提示。
                 良率是一种启发式尝试，旨在改善线程之间的相对进程，否则会过度使用CPU。
                 它的使用应该结合详细的分析和基准来确保
                   它实际上具有预期的效果。
                 使用这种方法很少合适。 这对于调试或测试目的可能是有用的，它可能有助于重现由于竞态条件而产生的错误。
                 在设计并发控制结构时，这也可能是有用的，例如
                   java.util.concurrent.locks包。

                 */
                Thread.yield();
                System.out.print("--");
                System.out.println(number);
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 42;
        Thread.sleep(1000L);
        number = -1;
        ready = true;
    }
}
