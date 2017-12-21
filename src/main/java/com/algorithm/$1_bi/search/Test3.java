package com.algorithm.$1_bi.search;

import com.algorithm.$5_json.JsonMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class Test3 {

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {

//        int maxInt = 1000000000;
//        new Thread(() ->{
//            serial(maxInt);
//            System.out.println(Thread.currentThread().getName());
//        },"serial thread").start();

//        new Thread(() ->{
//            parallel(maxInt);
//            System.out.println(Thread.currentThread().getName());
//        },"parallel thread").start();


//        int[] arr = Test3.parallel(20);
//        System.out.println(JsonMapper.toJson(arr));

        boolean primeByParallel = isPrimeByParallel(4);
        System.out.println(primeByParallel);
    }

    private static void serial(int maxInt) {
        long l = new Date().getTime();
        int[] ints = IntStream.range(2, maxInt).filter(i -> isPrime(i)).toArray();
        System.out.println("serial: " + (new Date().getTime() - l));
        IntSummaryStatistics asInt = Arrays.stream(ints).summaryStatistics();
        System.out.println(asInt);
    }

    public static int[] parallel(int maxInt) {
        long l = new Date().getTime();
        int[] ints = IntStream.range(2, maxInt).filter(i -> isPrimeByParallel(i)).toArray();

        System.out.println("parallel: " + (new Date().getTime() - l));
        IntSummaryStatistics asInt = Arrays.stream(ints).summaryStatistics();
        System.out.println(asInt);
        return ints;
    }

    public static boolean isPrime(int candidate) {
        int sqrt = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, sqrt).noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrimeByParallel(int candidate) {
        int sqrt = (int) Math.sqrt(candidate);
        //平方根的情况下，闭合，必须包涵最后一个数字，
        return IntStream.rangeClosed(2, sqrt).noneMatch(i -> candidate % i == 0);
    }

    public <T>List<T> takeWhile(List<T> list, Predicate predicate){
        int i = 0 ;
        for (T item : list) {
            if (!predicate.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }


}
