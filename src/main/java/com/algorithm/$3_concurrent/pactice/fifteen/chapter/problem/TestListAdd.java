package com.algorithm.$3_concurrent.pactice.fifteen.chapter.problem;

import com.algorithm.$3_concurrent.pactice.fifteen.chapter.SimulatedCAS;
import com.algorithm.$5_json.JsonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/2.
 * @version:v1.0
 */

public class TestListAdd {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            incrementByThreadAndAtomicInteger(list);
        }
        latch.await();
        System.out.println("final =" + atomic.get());
        System.out.println("list.size = " + list.size());
        System.out.println("concurrentHashMap.size = " + concur.size());
    }

    private static volatile List<Integer> list = new ArrayList<>();
    private static int count = 100000;
    private static ConcurrentHashMap<Integer, Integer> concur = new ConcurrentHashMap<>();
    private static AtomicInteger atomic = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void incrementByThreadAndAtomicInteger(List<Integer> set) {
        Thread thread = new Thread(() -> {
            try {
                int andIncrement = atomic.getAndIncrement();
                set.add(andIncrement);
                concur.put(andIncrement, andIncrement);
            } finally {
                latch.countDown();
            }
        }, Objects.toString(atomic.get()) + ": thread name.");
        thread.start();
    }
}
