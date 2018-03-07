package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.util.Arrays;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord("structure : 眼界格局")
public class $2018_01_13_structure {

    /**
     * 1: modules:  These courses cover a twelve-week period and are organised into three four-week modules
     * <p>
     * 2: 格局,眼界  structure
     */


    public static void main(String[] args) {
        int[] a = {5, 2, 7, 9, 60, 11, 33, 55, 35, 67, 76, 45, 34, 12};
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }


    /**
     * 插入排序,采用移位,而不是交换;
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        int j;
        for (int p = 0; p < a.length; p++) {
            int tmp = a[p];
            for (j = p; j > 0 && tmp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;

        }

    }

    public static void quickSort(int[] a,int left, int right){





    }

    public static int media3(int[] a, int element1,int element2){
        
    }




}
