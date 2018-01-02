package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    static AtomicStampedReference<Integer> atomic = new AtomicStampedReference(0, 0);

    public static void main(String[] args) throws InterruptedException {

//        http://blog.csdn.net/zhaozhirongfree1111/article/details/72781758
        final int stamp = atomic.getStamp();
        final Integer reference = atomic.getReference();
        System.out.println(reference + "============" + stamp);
        Thread t1 = new Thread(() ->
                System.out.println(reference + "-" + stamp + "-"
                        + atomic.compareAndSet(reference, reference + 10, stamp, stamp + 1))

        );

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer reference = atomic.getReference();
                System.out.println(reference + "-" + stamp + "-"
                        + atomic.compareAndSet(reference, reference + 10, stamp, stamp + 1));
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(atomic.getReference());
        System.out.println(atomic.getStamp());
    }
}