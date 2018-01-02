package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanhb on 2018/1/1
 * @desc xxxx
 */
public class CasCounter {
    private SimulatedCAS cas = new SimulatedCAS();


    public int get() {
        return cas.get();
    }

    public int increment() {
        int oldValue;
        int newValue;

        do {
            oldValue = cas.get();
            newValue = oldValue + 1;
        } while (!cas.compareAndSet(oldValue, newValue));//不成功，继续循环尝试
        // } while (oldValue != cas.compareAndSwap(oldValue, newValue));//源码
        return newValue;
    }

    public static void main(String[] args) throws InterruptedException {
        CasCounter casCounter = new CasCounter();
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(casCounter.increment());
//        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            incrementByThread(casCounter, set);
        }
        latch.await();
        System.out.println("final number = " + atomic.get());
        System.out.println("set size = " + set.size());

    }

    private static AtomicInteger atomic = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(100000);

    public static void incrementByThread(CasCounter casCounter, Set<Integer> set) {
        Thread thread = new Thread(() -> {
            try {
                int andIncrement = atomic.getAndIncrement();
                int casNumber = casCounter.get();
                System.out.println(Thread.currentThread().getName() + casNumber + " == " + andIncrement);
                int increment = casCounter.increment();
                set.add(increment);

            } finally {
                latch.countDown();
            }

        }, Objects.toString(casCounter.get()) + ": thread name.");
        thread.start();
    }
}
