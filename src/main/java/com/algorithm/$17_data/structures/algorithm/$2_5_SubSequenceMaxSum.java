package com.algorithm.$17_data.structures.algorithm;

/**
 * @author fanhb on 2018/1/6
 * @desc xxxx
 */
public class $2_5_SubSequenceMaxSum {


    /**
     * 第二种算法
     */


    public int getMaxSubSequence(int[] a) {

        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {

            int thisSum = 0;
            for (int j = i; j < a.length; j++) {

                thisSum += a[j];
                if (thisSum > maxSum)
                    maxSum = thisSum;

            }
        }


        return maxSum;
    }

    /**
     * 第四种算法
     */

    public int getForthAlgorithm(int[] a) {
        int maxSum = 0;
        int thisSum = 0;
        int begin = 0, end = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (maxSum < thisSum) {
                if (thisSum < 0 || maxSum <= 0)
                    begin = i;
                maxSum = thisSum;
                end = i;
            } else if (thisSum < 0) {
                begin = i;
                thisSum = 0;

            }
        }

        System.out.println("max = " + maxSum + ", begin = " + begin + ", end = " + end);
        return maxSum;

    }

    public static void main(String[] args) {
        int[] arr = {-5, -1, -1, -1, -4, 3, -5, -2, -1, -2, -6, -2, -0};
//        int[] arr = {-5, 0, 1, -1, 4, -3, 5, -2, -1, 2, 6, -2, 11};
        new $2_5_SubSequenceMaxSum().getForthAlgorithm(arr);

    }

}
