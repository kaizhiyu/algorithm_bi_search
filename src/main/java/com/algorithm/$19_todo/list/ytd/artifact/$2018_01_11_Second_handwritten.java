package com.algorithm.$19_todo.list.ytd.artifact;

import com.algorithm.$8_annotation.single.ann.ETT;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth v_fanhaibo on   2018/1/11
 */
@ETT("algorithm")
public class $2018_01_11_Second_handwritten {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15};
        summation(a, 15);
    }

    @ETT("欧几里算法 2018年1月11日10:16:22")
    public static int gcd(int m, int n) {
        if (m % n == 0)
            return n;
        else
            return gcd(n, m % n);
    }

    @ETT("两个数求和")
    public static  Map<Integer, Integer> summation(int[] a, int sum) {
        Map<Integer, Integer> pair = new HashMap<>();
        int s = 0, e = a.length - 1;
        while (s < e) {
//            System.out.println("s=" + s + "，e=" + e);
            int sv = a[s];
            int ev = a[e];
            int sumVV = sv + ev;
            if (sumVV < sum) {
                s++;
            } else if (sumVV > sum) {
                e--;
            } else {
                pair.put(sv, ev);
                s++;
            }
        }
        return pair;
    }
}
