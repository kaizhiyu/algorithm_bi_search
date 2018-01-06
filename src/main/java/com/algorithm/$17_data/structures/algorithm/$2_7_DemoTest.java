package com.algorithm.$17_data.structures.algorithm;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author fanhb on 2018/1/6
 * @desc xxxx
 */
public class $2_7_DemoTest {

    public static void main(String[] args) {

        int[] arr = {4, -3, 5, -2, -1, 2, 6, -2};
        System.out.println(new $2_7_DemoTest().getMaxSumRec(arr, 0, arr.length-1));

    }

    public int getMaxSumRec(int[] arr, int left, int right) {

        if (left == right)
            if (arr[left] > 0)
                return arr[left];
            else
                return 0;


        int center = (left + right) / 2;


        int maxLeftSum = getMaxSumRec(arr, left, center);
        int maxRightSum = getMaxSumRec(arr, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {//包含
            leftBorderSum += arr[i];
            if (maxLeftBorderSum < leftBorderSum) maxLeftBorderSum = leftBorderSum;
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (maxRightBorderSum < rightBorderSum) maxRightBorderSum = rightBorderSum;
        }
        int maxBorder = maxLeftBorderSum + maxRightBorderSum;
        return Math.max(maxBorder, Math.max(maxRightSum, maxLeftSum));
    }


}
