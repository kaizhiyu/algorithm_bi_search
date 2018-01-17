package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord(" mock test code ")
public class $2018_01_17_binarySystem {
    public static void main(String[] args) {

        int num = 257;
        printFormat(num);
        printFormat(129);
        printFormat(127);
        printFormat(-1);
        printFormat((byte) 0b00000000);


        String fs = String.format("身高体重(%.2f , %d)", 173.2, 65);
        System.out.println(fs);
        String s = "abc";
        System.out.println(s + String.format("%1$01d", 0));
    }

    private static void printFormat(int num) {
        System.out.printf("%32d, binary code=%32s, %s", (byte) num, Integer.toBinaryString(num), "\n");
    }

    public static void ssss() {
// TODO Auto-generated method stub
        int n = 6;
        String s = "abc";
        System.out.println("%1$0" + (n - s.length()) + "d");
        System.out.println(s + String.format("%1$0" + (n - s.length()) + "d", 0));
    }
}