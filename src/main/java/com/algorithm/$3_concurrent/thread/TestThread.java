package com.algorithm.$3_concurrent.thread;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/21.
 * @version:v1.0
 */

public class TestThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"normal thread name: ");
        thread.start();

        try {
            System.out.println("main wait to be joined");
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getName()+" is dead!!!");
    }
}
