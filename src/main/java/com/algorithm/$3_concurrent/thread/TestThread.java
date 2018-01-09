package com.algorithm.$3_concurrent.thread;

import com.google.common.util.concurrent.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

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
        }, "normal thread name: ");
        thread.start();

        try {
            System.out.println("main wait to be joined");
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getName() + " is dead!!!");
    }


    @Test
    public void test1() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3,(runnable)-> {
            AtomicLong index = new AtomicLong(0);
            return new Thread(runnable, "commons-thread-" + index.incrementAndGet());
        });
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(threadPool);
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(() -> { Thread.sleep(3000);/*System.out.println(1 / 0);*/return "world"; });
        //1)监听
        listenableFuture.addListener(()-> System.out.println("can't get return value"), MoreExecutors.directExecutor());
        //2)回调
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override public void onSuccess(String result) { System.out.println("the result of future is: " + result); }
            @Override public void onFailure(Throwable t) { System.out.println("exception:" + t.getMessage()); }
        });
        Thread.sleep(5000);
    }
}
