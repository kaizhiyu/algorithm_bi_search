package com.algorithm.$6_lock;

import java.util.Date;

/**
 * 14.1 状态依赖性的管理
 * 1：可阻塞的状态依赖操作的结构
 * 2：
 *
 * @author:v_fanhaibo on 2017/12/21.
 * @version:v1.0
 * this topic: 状态依赖性有界限缓存管理
 */

public class SecMyBlockingQueue<T> extends BaseBlockingQueue<T> {

    public SecMyBlockingQueue(int capacity) {
        super(capacity);
    }


    protected synchronized void put(T item) throws InterruptedException {
        System.out.println("SecMyBlockingQueue.put");
        while (isFull())
            wait();
        super.doPut(item);
        notifyAll();
    }


    protected synchronized T get() throws InterruptedException {
        System.out.println("SecMyBlockingQueue.get");
        /**
         *  试sleep不释放锁  Thread.sleep(100000);
         */
        while (isEmpty())
            wait();
        notifyAll();
        return super.doTake();
    }

    public static void main(String[] args) {
        SecMyBlockingQueue<Integer> queue = new SecMyBlockingQueue<>(3);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        queue.put(i++);
                        System.out.println(Thread.currentThread().getName() + ": get n=" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread thread2 = getThreadMethod(queue);
        Thread thread3 = getThreadMethod(queue);
        Thread thread4 = getThreadMethod(queue);
        Thread thread5 = getThreadMethod(queue);


        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread.start();

    }

    private static Thread getThreadMethod(SecMyBlockingQueue<Integer> queue) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Integer integer = queue.get();
                        System.out.println(Thread.currentThread().getName() + ": get n=" + integer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
