package com.algorithm.$1_bi.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.font.TrueTypeFont;

import java.util.Arrays;
import java.util.Random;

/**
 * Title:
 * Searches the specified array of ints for the specified value using the binary search algorithm.
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class MyBiSearch {

    public static int biSearch(int[] arr, int key) {
        int indexByBiSearch = getIndexByBiSearch(arr, key, 0, arr.length);
        return indexByBiSearch;
    }


    private static int getIndexByBiSearch(int[] arr, int key, int fromIndex, int toIndex) {
        if (fromIndex <= toIndex) {
            int mid = (toIndex - fromIndex) / 2 + fromIndex;
            int midElement = arr[mid];
            if (key > midElement) {
                return getIndexByBiSearch(arr, key, mid + 1, toIndex);
            } else if (key < midElement) {
                return getIndexByBiSearch(arr, key, fromIndex, mid - 1);
            } else {
                return mid;//index found
            }
        }
        return -(fromIndex + 1);
    }


    public static int cycleBiSearch(int[] arr, int key) {
        int fromIdex = 0;
        int toIndex = arr.length;
        while (fromIdex <= toIndex) {
            int mid = (toIndex - fromIdex) / 2 + fromIdex;
            int num = arr[mid];
            if (key > num) {
                fromIdex = mid + 1;
            } else if (key < num) {
                toIndex = mid - 1;
            } else {
                return mid;
            }
        }
        return -(fromIdex + 1);

    }

    /**
     * Generates the next pseudorandom number. Subclasses should override this, as this is used by all other methods.
     * The general contract of next is that it returns an int value and if the argument bits is between 1 and 32 (inclusive),
     * then that many low-order bits of the returned value will be (approximately) independently chosen bit values,
     * each of which is (approximately) equally likely to be 0 or 1. The method next is implemented by class Random by atomically updating the seed to
     * (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
     * and returning (int)(seed >>> (48 - bits)).
     * This is a linear congruential pseudorandom number generator, as defined by D. H. Lehmer and described by Donald E.
     * Knuth in The Art of Computer Programming, Volume 3: Seminumerical Algorithms, section 3.2.1.
     */
    public static int[] getArr(int length, boolean... isBigData) {
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {

            int ele;
            if (isBigData != null && isBigData.length != 0) {//
                ele = random.nextInt();                 //big data
            } else {
                ele = random.nextInt(length<<1);//small data
            }
            arr[i] = ele;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        int[] arr = getArr(20);
//        int[] arr = {2,4,8,9,9,11,15,16,18,19,19,21,23,30,33,33,34,40,42,48};
        int ele = arr[0];
        System.out.println("key: " + ele + ",arr: " + mapper.writeValueAsString(arr));
        int i = biSearch(arr, ele);
        System.out.println(i);
    }
}
