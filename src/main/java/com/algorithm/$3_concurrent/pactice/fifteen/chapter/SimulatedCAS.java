package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$5_json.JsonMapper;
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
public class SimulatedCAS {
    private int value;
    public synchronized int get() {
        return this.value;
    }
    public synchronized int compareAndSwap(int expectedValue, int newVal) {
        int oldValue = this.value;
        if (expectedValue == oldValue) {
            this.value = newVal;
        }//
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newVal) {

        return (expectedValue == compareAndSwap(expectedValue, newVal));
    }


    public static void main(String[] args) throws InterruptedException {
        SimulatedCAS cas = new SimulatedCAS();
//        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < count; i++) {
//            incrementByThread(cas, list);

        }
        latch.await();

        System.out.println("final =" + atomic.get());
        System.out.println("set size = " + list.size());
        System.out.println("concurrentHashMap.size = " + concur.size());
        System.out.println(JsonMapper.toJson(concur));
    }
    static volatile List<Integer> list = new ArrayList<>();
    static int count = 100000;
    static ConcurrentHashMap<Integer,Integer> concur= new ConcurrentHashMap<>();
    private static AtomicInteger atomic = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void incrementByThread(SimulatedCAS cas, List<Integer> set) {
        Thread thread = new Thread(() -> {
            try {
                int andIncrement = atomic.getAndIncrement();
                int oldValue;
                int newValue;
                do {
                    oldValue = cas.get();
                    newValue = oldValue + 1;
                } while (cas.compareAndSwap(oldValue, newValue) != oldValue);
                set.add(newValue);
                concur.put(andIncrement,oldValue);
//                System.out.println("atomic Integer = " + andIncrement + ", old Value = " +  oldValue);
            } finally {
                latch.countDown();
            }

        }, Objects.toString(cas.get()) + ": thread name.");
        thread.start();
    }

}
