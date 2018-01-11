package com.algorithm.$3_concurrent.pactice.fifteen.chapter.problem;

import com.algorithm.$8_annotation.single.ann.Doc4Desc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第十五章末尾，
 * ArrayList.add 中的 elementData[size++] = e; size++并不是安全的
 * @author:v_fanhaibo on 2018/1/2.
 * @version:v1.0
 *
 * DONE
 */

@Doc4Desc("DONE ArrayList.add.size++ is unsafe in multi threads.")
public class Unsafe_TestListAdd {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            incrementByThreadAndAtomicInteger(vector);
        }
        latch.await();
        System.out.println("final =" + atomic.get());
        System.out.println("list.size = " + list.size());
        System.out.println("vector.size = " + vector.size());
        System.out.println("concurrentHashMap.size = " + concur.size());
    }

    private static volatile List<Integer> list = new ArrayList<>();
    private static volatile Vector<Integer> vector = new Vector<>();
    private static int count = 100000;
    private static ConcurrentHashMap<Integer, Integer> concur = new ConcurrentHashMap<>();
    private static AtomicInteger atomic = new AtomicInteger(0);
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void incrementByThreadAndAtomicInteger(List<Integer> set) {
        Thread thread = new Thread(() -> {
            try {
                int andIncrement = atomic.getAndIncrement();
                //elementData[size++] = e; size++并不是安全的
                set.add(andIncrement);
                concur.put(andIncrement, andIncrement);
            } finally {
                latch.countDown();
            }
        }, Objects.toString(atomic.get()) + ": thread name.");
        thread.start();
    }
}
