package com.algorithm.$1_bi.search;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/18.
 * @version:v1.0
 */

public class ThreadBinarySearch {

    private static ExecutorService service =  Executors.newCachedThreadPool();
    public static void main(String[] args) {
        int[] arr = MyBiSearch.getArr(20);
        System.out.println(arr);
    }

}
