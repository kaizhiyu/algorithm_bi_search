package com.test.demo;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/8.
 * @version:v1.0
 */

public class DemoTest {


    /**
     * 第四种算法 2018年1月8日10:10:27
     *
     * @param a
     */
    public static void getMaxSubSequence(int[] a) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
    }


}
