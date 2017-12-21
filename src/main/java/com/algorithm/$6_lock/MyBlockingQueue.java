package com.algorithm.$6_lock;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/21.
 * @version:v1.0
 */

public class MyBlockingQueue<T> extends BaseBlockingQueue<T> {

    public MyBlockingQueue(int capacity) {
        super(capacity);
    }



    protected void put(T item) throws InterruptedException {
        while (true){
            synchronized (this){
                if (!isFull())
                super.doPut(item);
            }
            Thread.sleep(1000);
        }
    }


    protected T get() throws InterruptedException {

        while(true){
            synchronized(this){
                if (!isEmpty()){
                    return super.doTake();
                }
            }
            Thread.sleep(1000);
        }

    }
}
