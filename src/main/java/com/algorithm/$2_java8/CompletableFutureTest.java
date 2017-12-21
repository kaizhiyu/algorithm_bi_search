package com.algorithm.$2_java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/20.
 * @version:v1.0
 */

public class CompletableFutureTest {

    private static Random rand = new Random();

    static int getMoreData() {

        System.out.println(1 / 0);

        return rand.nextInt(1000);

    }

    public static void main(String[] args) throws Exception {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1 / 0);
            }
        },"sss");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
               e.printStackTrace();
                System.out.println(e.getCause());
                System.out.println(e);
            }
        });
        thread.start();

    }
}
