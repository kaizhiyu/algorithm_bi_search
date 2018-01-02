package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$8_annotation.ParaDesc;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 出来ABA问题
 */
public class C15_END_AtomicStampedReferenceTest {
    static AtomicStampedReference<Integer> atomic = new AtomicStampedReference(0, 0);

    public static void main(String[] args) throws InterruptedException {

//        http://blog.csdn.net/zhaozhirongfree1111/article/details/72781758
        @ParaDesc("stamp:标记。 相当于版本号") final int stamp = atomic.getStamp();
        @ParaDesc("obj is reference,是一个引用地址。") final Integer obj = atomic.getReference();
        atomic.compareAndSet(obj, obj + 10, stamp, stamp + 1);
        System.out.println(obj + "============" + stamp);


        @ParaDesc("expectedReference：期望的引用，newReference:引用的新值")
        @ParaDesc("expectedStamp：期望的标记，newStamp:标记的新值")
        @ParaDesc("newStamp：the new value for the stamp")
        Thread t1 = new Thread(() -> System.out.println(atomic.compareAndSet(obj, obj + 10, stamp, stamp + 1)));

        Integer obj2 = atomic.getReference();
        Integer stamp2 = atomic.getStamp();
        Thread t2 = new Thread(() -> System.out.println(atomic.compareAndSet(obj2, obj2 + 10, stamp2, stamp2 + 1)));
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(atomic.getReference());
        System.out.println(atomic.getStamp());
    }
}