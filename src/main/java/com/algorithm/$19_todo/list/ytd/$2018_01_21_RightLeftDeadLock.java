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


    int hashRight = System.identityHashCode(right);
    int hashLeft = System.identityHashCode(left);

    public void getRightLeft() {
        if (hashRight > hashLeft) {

            synchronized (right) {
                synchronized (left) {
                    IAmRightRleft();
                }
            }
        } else {

            synchronized (left) {
                synchronized (right) {
                    IAmRightRleft();
                }
            }
        }
    }

    private void IAmRightRleft() {
        System.out.println("i am rightLeft ");
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLeftRight() {
        if (hashRight < hashLeft) {

            synchronized (left) {
                synchronized (right) {
                    iAmLeftRigt();
                }
            }
        } else {

            synchronized (right) {
                synchronized (left) {
                    iAmLeftRigt();
                }
            }
        }
    }

    private void iAmLeftRigt() {
        try {
            System.out.println("i am leftRight ");
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
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
