package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$8_annotation.Doc4Desc;
import com.algorithm.$8_annotation.ThreadSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanhb on 2018/1/1
 * @desc 程序清单: 15-1
 */
@Doc4Desc("模拟cas,只表示语义,不表示性能")
@ThreadSafe
public class C15_1_SimulatedCAS {
    private int value;

    public synchronized int get() {
        return this.value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newVal) {
        int oldValue = this.value;
        if (expectedValue == oldValue)
            this.value = newVal;
 
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newVal) {
        return (expectedValue == compareAndSwap(expectedValue, newVal));
    }

    public static void main(String[] args) throws InterruptedException {
        C15_1_SimulatedCAS cas = new C15_1_SimulatedCAS();
        for (int i = 0; i < count; i++) {
            incrementByThread(cas, arr, list);
        }
        latch.await();
        System.out.println("final = " + atomic.get());
        System.out.println("arr size = " + arr.length);
        System.out.println("list size = " + list.size());
        System.out.println("concurrentHashMap.size = " + concur.size());
//        System.out.println(JsonMapper.toJson(arr));
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        System.out.println("set.size = " + set.size());

    }

    static int count = 100000;
    static volatile Integer[] arr = new Integer[count];
    static volatile List<Integer> list = new ArrayList<>(count);
    static ConcurrentHashMap<Integer, Integer> concur = new ConcurrentHashMap<>();
    private static AtomicInteger atomic = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void incrementByThread(C15_1_SimulatedCAS cas, Integer[] set, List<Integer> list) {
        Thread thread = new Thread(() -> {
            try {
                int andIncrement = atomic.getAndIncrement();
                int oldValue;
                int newValue;
                do {
                    oldValue = cas.get();
                    newValue = oldValue + 1;
                } while (cas.compareAndSwap(oldValue, newValue) != oldValue);
                set[oldValue] = oldValue;
                list.add(oldValue);
                concur.put(andIncrement, oldValue);
//                System.out.println("atomic Integer = " + andIncrement + ", old Value = " +  oldValue);
            } finally {
                latch.countDown();
            }

        }, Objects.toString(cas.get()) + ": thread name.");
        thread.start();
    }

}
