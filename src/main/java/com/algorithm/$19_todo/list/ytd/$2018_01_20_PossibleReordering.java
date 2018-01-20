package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord(" jdk1.7 forkJoinPool ")
@SearchKeyWord(" 重排序,JMM ")

public class $2018_01_20_PossibleReordering {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static int x = 0, y = 0, a = 0, b = 0;
    public static void main(String[] args) {
        reordering(1);
        reorderingB(2);
        System.out.printf("a=%d,b=%d,x=%d,y=%d, \n", a, b, x, y);
        reorderingB(3);
        reorderingB(43);
        reorderingB(45);
        System.out.printf("a=%d,b=%d,x=%d,y=%d, \n", a, b, x, y);
        System.out.println("$2018_01_20_PossibleReordering.main");
    }
    public static void reordering(int num) {
        threadPool.submit(() -> { a = num;x = a; });
    }

    public static void reorderingB(int temp) {
        threadPool.submit(() -> { b = temp;y = b; });
    }
}
