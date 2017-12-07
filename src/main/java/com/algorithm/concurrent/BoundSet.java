package com.algorithm.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 自己实现有界队列
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class BoundSet<T> {
    private final Semaphore sem;
    private final Set<T> continer ;

    public BoundSet(int bound) {
        sem = new Semaphore(bound);
        continer = Collections.synchronizedSet(new HashSet<>());
    }
    public boolean add(T a) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = continer.add(a);
            return wasAdded;
        }finally {
            if (!wasAdded){
                sem.release();
            }
        }


    }


    public boolean remove(T a){
        boolean remove = continer.remove(a);
        if (remove){
            sem.release();
        }
        return remove;
    }



}
