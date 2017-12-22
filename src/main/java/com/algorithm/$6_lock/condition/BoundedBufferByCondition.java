package com.algorithm.$6_lock.condition;

import com.algorithm.demo.GuardedBy;
import com.algorithm.demo.ThreadSafe;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */
@ThreadSafe
public class BoundedBufferByCondition<T> {
    private ReentrantLock lock = new ReentrantLock();
    @GuardedBy("lock")
    private Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private Condition notFull = lock.newCondition();

    private int capacity;
    private T[] buffer = (T[]) new Object[capacity];
    private int head;
    private int tail;
    private int count;

    public BoundedBufferByCondition(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
        System.out.println("BoundedBufferByCondition.put-" + new Date());
        lock.lock();
        try {
            while (count == buffer.length)//
                notFull.await();
            buffer[tail] = item;
            if (++tail == buffer.length) {
                tail = 0;
            }
            notEmpty.signal();
            count++;
        } finally {
            lock.unlock();
        }

    }

    public T get() throws InterruptedException {
        System.out.println("BoundedBufferByCondition.get-" + new Date());
        lock.lock();
        try {
            while (count == 0)//while //需要多次理解
                notEmpty.await();
            T x = buffer[head];
            if (++head == buffer.length) {
                head = 0;
            }
            count--;
            notFull.signal();//If any threads are waiting on this condition then one is selected for waking up.
            return x;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        BoundedBufferByCondition<Long> queue = new BoundedBufferByCondition<>(3);
        Thread thread = putThreadBy2Seconds(queue, "put 71 lines=======");


//        Thread thread2 = getThreadMethodBy1Second(queue);
//        Thread thread3 = getThreadMethodBy1Second(queue);
//        Thread thread4 = getThreadMethodBy1Second(queue);
        Thread thread5 = getThreadMethodBy1Second(queue, "get 77 lines=======");


//        thread2.start();
//        thread3.start();
//        thread4.start();
        thread5.start();

        thread.start();

    }

    private static Thread putThreadBy2Seconds(BoundedBufferByCondition<Long> queue, String threadName) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Long i = 0L;
                while (true) {
                    try {
                        queue.put(i++);
                        System.out.println(Thread.currentThread().getName() + ": get n=" + i);

                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, threadName);
    }

    private static Thread getThreadMethodBy1Second(BoundedBufferByCondition<Long> queue, String threadName) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Long integer = queue.get();
                        Thread.sleep(1000L);
                        System.out.println(Thread.currentThread().getName() + ": get n=" + integer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, threadName);
    }
}
