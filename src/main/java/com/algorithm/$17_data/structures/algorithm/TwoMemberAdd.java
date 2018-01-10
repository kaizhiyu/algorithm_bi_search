package com.algorithm.$17_data.structures.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理两个数求和的问题
 *
 * @author:v_fanhaibo on 2018/1/10.
 * @version:v1.0
 */

public class TwoMemberAdd {
    //    http://blog.csdn.net/v_JULY_v/article/details/6419466

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 11, 15};

//        new TwoMemberAdd().findSum(arr)
    }

    public List<Pair> findSum(int[] arr, int n, int x) {
        List<Pair> list = new ArrayList<>();
        //sort(s,s+n);   如果数组非有序的，那就事先排好序O（N*logN）
        int begin = 0;
        int end = arr.length - 1;
        while (begin < end)    //俩头夹逼，或称两个指针两端扫描法，很经典的方法，O（N）
        {
            if (arr[begin] + arr[end] > x) {
                --end;
            } else if (arr[begin] + arr[end] < x) {
                ++begin;
            } else {
                list.add(new Pair(arr[begin], arr[end]));
            }
        }
        return list;
    }

    static class Pair {
        int f, s;

        public Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
