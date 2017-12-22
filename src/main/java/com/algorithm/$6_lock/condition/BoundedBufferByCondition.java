package com.algorithm.$6_lock.condition;

import com.algorithm.$8_annotation.GuardedBy;
import com.algorithm.$8_annotation.ThreadSafe;
import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java 并发编程实战
 * PageNo:252,程序清单14-11
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */
@ThreadSafe
public class BoundedBufferByCondition<T> {
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private T[] buffer;
    @GuardedBy("lock")
    @GuardedBy("lock")
    @GuardedBy("lock")
    private int head, tail, count;

    public BoundedBufferByCondition(int capacity) {
        buffer = (T[]) new Object[capacity];
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length)
                notFull.await();
            buffer[tail] = item;
            if (++tail == buffer.length)
                tail = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)  //while //需要多次理解
                notEmpty.await();
            T x = buffer[head];
            buffer[head] = null;
            if (++head == buffer.length)
                head = 0;
            --count;
            notFull.signal();//If any threads are waiting on this condition then one is selected for waking up.
            return x;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBufferByCondition<Long> queue = new BoundedBufferByCondition<>(3);
        Thread thread = putThreadBy2Seconds(queue, "put 71 lines=======");

        Mutex sync = new Mutex();
        ReentrantLock lock = new ReentrantLock();
        Semaphore semaphore = new Semaphore(0);
//        semaphore.acquire
//        long oneCentury = Sync.ONE_CENTURY;
//        Thread thread2 = getThreadMethodBy1Second(queue);
//        Thread thread3 = getThreadMethodBy1Second(queue);
//        Thread thread4 = getThreadMethodBy1Second(queue);
        Thread thread5 = getThreadMethodBy1Second(queue, "get 77 lines==");


//        thread2.start();
//        thread3.start();
//        thread4.start();
        thread5.start();

        thread.start();

    }

    private static Thread putThreadBy2Seconds(BoundedBufferByCondition<Long> queue, String threadName) {
        return new Thread(() -> {
            Long i = 9L;
            while (true) {
                try {
                    queue.put(i++);
                    System.out.println(Thread.currentThread().getName() + ": set n=" + i);

                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, threadName);
    }

    private static Thread getThreadMethodBy1Second(BoundedBufferByCondition<Long> queue, String threadName) {
        return new Thread(() -> {
            while (true) {
                try {
                    Long integer = queue.get();
                    Thread.sleep(2000L);
                    System.out.println(Thread.currentThread().getName() + ": get n=" + integer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, threadName);
    }
}
