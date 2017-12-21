package com.algorithm.$6_lock;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/21.
 * @version:v1.0
 */

public class BaseBlockingQueue<T> {

    private final T[] buffer;
    private int tail;
    private int head;
    private int count;

    protected BaseBlockingQueue(int capacity) {
        this.buffer = (T[]) new Object[capacity];
    }

    protected synchronized void doPut(T item) {
        buffer[tail] = item;
        count++;
        if (++tail == buffer.length) {
            tail = 0;
        }
    }

    protected synchronized T doTake() {
        T item = buffer[head];
        count--;
        if (++head == buffer.length) {
            head = 0;
        }
        return item;
    }

    public synchronized boolean isFull() {
        return count == buffer.length;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

}
