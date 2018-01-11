package com.algorithm.$0_math;

import com.algorithm.$8_annotation.single.ann.Doc4Desc;
import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.util.ArrayList;
import java.util.List;

/**
 * 谷歌方法论 两个球和三个球
 * @author:v_fanhaibo on 2018/1/10.
 * @version:v1.0
 */
@SearchKeyWord("two ball 求和")
public class LimitFromTwoBallsTest {


    public static void main(String[] args) {
        //两个球
        // ∑x = 100; ∑1 = x;
        // f(x) = 100/x + x;

        //三个球
        // 求x,y,z之和的最小值；
        // ∑x = 100; ∑y = xy ∑1=y;

        //f(x) = 100/x + x/y+y

        List<Double> ret = new ArrayList<>();
        List<Double> ret2 = new ArrayList<>();
        for (int y = 2; y < 10; y++) {
            //-100/x^2 + 1/y = 0;
            double sqrt = Math.sqrt(100 * y);
//            int x = (int)Math.sqrt(100*y);
//            ret.add(x);
            System.out.println("x=" + sqrt + ",y=" + y);
            double intRet = (100 / sqrt + sqrt / y + y);
            ret.add(intRet);
            double min = 100 / Math.sqrt(100 * y) + Math.sqrt(100 * y) / y + y;
            ret2.add(min);
        }
//        Collections.sort(ret);
        System.out.println(ret);
        System.out.println(ret2);
//        f(x) = 100/x + x/4+4
//        f(x) = 100/x + x/5+5
        System.out.println("===========5==========");
        System.out.println(  100.0/23.0 + 23.0/5.0+5);
        System.out.println(  100.0/22.0 + 22.0/5.0+5);
        System.out.println("===========4==========");
        System.out.println(  100.0/23.0 + 23.0/4.0+4);
        System.out.println(  100.0/22.0 + 22.0/4.0+4);
        System.out.println(  100.0/20.0 + 20.0/4.0+4);
    }
}
