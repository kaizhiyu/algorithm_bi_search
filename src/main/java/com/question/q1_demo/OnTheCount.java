package com.question.q1_demo;

import java.text.DecimalFormat;

/**
 * @auth v_fanhaibo on   2018/3/16
 */
public class OnTheCount {

    public static void main(String[] args) {
//        powerOfTwo(6);
//        powerOfTwo(5);
//        powerOfTwo(-4);
//        powerOfTwo(3);
//        powerOfTwo(2);
//        powerOfTwo(1);
//        powerOfTwo(0);

//        System.out.println(0b10000000000000000000000000000010);
//        System.out.println(Integer.toBinaryString(0b11111111111111111111111111110111));
//        System.out.println(Integer.toBinaryString(0b11111111111111111111111111111011));
//        System.out.println(0b11111111111111111111111111111011);
//        System.out.println(0b11111111111111111111111111111101);
//        System.out.println(0b11111111111111111111111111111110);
//        0b01100101
//        0b00101011
//        System.out.println(Integer.toBinaryString( 0b00101011 & 0b01100101));
//        System.out.println(Integer.toBinaryString(0b00101011 | 0b01100101));

        int A = 0b00101011, B = 0b01100101;
        int C = A & B;//0010 0001；
        int D = A | B;//0110 1111；
        int E = C ^ D;//0100 1110；

        System.out.println(String.format("%8s",Integer.toBinaryString(A)));
        System.out.println(String.format("%8s",Integer.toBinaryString(B)));
        System.out.println(String.format("%8s",Integer.toBinaryString(C)));
        System.out.println(String.format("%8s",Integer.toBinaryString(D)));
        System.out.println(String.format("%8s",Integer.toBinaryString(E)));
        System.out.println(String.format("%08d",1));
    }

    public static void powerOfTwo(int n) {
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n - 1));
        System.out.println(n & (n - 1));
    }
}
