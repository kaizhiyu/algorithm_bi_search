package com.algorithm.$17_data.structures.algorithm;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/9.
 * @version:v1.0
 */

public class $2_10_GreatestCommonDivisor {


    public static void main(String[] args) {
        System.out.println(gcd(1989L, 1590L));
        System.out.println(gcd(33L, 22L));
    }

    /**
     * m>n 大的数字放前面
     *
     * @param m
     * @param n
     * @return
     */
    public static long gcd(long m, long n) {
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public int divisor(int x, int y) {
        if (x % y == 0) {
            return y;
        } else {
            return divisor(y, x % y);
        }
    }
//    定理：两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。最大公约数（Greatest Common Divisor）缩写为GCD。
//     a可以表示成a = kb + r（a，b，k，r皆为正整数，且r<b），则r = a mod b

//    证法一
//    假设d是a,b的一个公约数，记作d|a,d|b，即a和b都可以被d整除。
//    而r = a - kb，两边同时除以d，r/d=a/d-kb/d=m，由等式右边可知m为整数，因此d|r
//    因此d也是b,a mod b的公约数
//    假设d是b,a mod b的公约数, 则d|b,d|(a-k*b),k是一个整数。
//    进而d|a.因此d也是a,b的公约数
//    因此(a,b)和(b,a mod b)的公约数是一样的，其最大公约数也必然相等，得证。
//
//    第一步：令c=gcd(a,b)，则设a=mc，b=nc
//    第二步：可知r =a-kb=mc-knc=(m-kn)c
//    第三步：根据第二步结果可知c也是r的因数
//                  b = nc，r = (m-kn)c

//    第四步：可以断定m-kn与n互素
//      【否则，可设m-kn=xd,n=yd,(d>1)，
//          则m=kn+xd=kyd+xd=(ky+x)d，
//          则a=mc=(ky+x)dc，b=nc=ycd，
//          故a与b最大公约数≥cd，而非c，
//          与前面结论矛盾】
//    从而可知gcd(b,r)=c，继而gcd(a,b)=gcd(b,r)，得证
//    注意:两种方法是有区别的。


}
