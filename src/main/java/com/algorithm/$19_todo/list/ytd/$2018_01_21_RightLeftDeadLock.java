package com.algorithm.$19_todo.list.ytd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */
public class $2018_01_21_RightLeftDeadLock {
    /**  */
    private final Object right = new Object();
    private final Object left = new Object();


    public void getRightLeft() {
        synchronized (right) {
            synchronized(left){
                System.out.println("i am rightLeft ");
                try {
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getLeftRight() {
        synchronized (left) {
            synchronized (right){
                try {
                    System.out.println("i am leftRight ");
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        $2018_01_21_RightLeftDeadLock ins = new $2018_01_21_RightLeftDeadLock();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.submit(() -> {

            while (true) {
                ins.getLeftRight();
            }
        });
        threadPool.submit(() -> {
            while (true) {
                ins.getRightLeft();
            }
        });

    }
}
