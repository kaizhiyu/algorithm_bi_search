package com.algorithm.$2_java8.executor;

import com.algorithm.$8_annotation.single.ann.Location;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Location("ForkJoinPool & CompletableFuture.complete/completeExceptionally(ex)")
public class Executor_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long l = new Date().getTime();
        CompletableFuture<Integer> done1 = CompletableFuture.supplyAsync(Executor_Future::delayOneMillisecond);
        CompletableFuture<Integer> done2 = CompletableFuture.supplyAsync(Executor_Future::delayOneMillisecond);
        CompletableFuture<Integer> done3 = CompletableFuture.supplyAsync(()->new Executor_Future().delay());
        System.out.println("time = "+(new Date().getTime() - l));
        System.out.println( done1.get()+ done2.get()+done3.get());
        System.out.println("time = "+(new Date().getTime() - l));

    }

    public static Integer delayOneMillisecond(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }
    public Integer delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }

}
