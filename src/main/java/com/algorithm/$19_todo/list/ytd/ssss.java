package com.algorithm.$19_todo.list.ytd;

import javazoom.jl.player.Player;

import java.io.*;

public class ssss {

    public static void main(String[] args) throws IOException {

//        String key = "key";
//        int code2 = (key.hashCode() >>> 16);
//        int code1 = key.hashCode();
//        System.out.println(Integer.toBinaryString(code2));
//        System.out.println(Integer.toBinaryString(code1));
//        int code = (key.hashCode() ^ (key.hashCode() >>> 16));
//        int code3 = code1 ^ code2;
//        System.out.println(code3);
//
//        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(0b110^0b001));
//        System.out.println(Integer.toBinaryString(2^0));
//        System.out.println(Integer.toBinaryString(2|0));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read = byteArrayInputStream.read("".getBytes());
        byteArrayOutputStream.write("".getBytes());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
//        dataInputStream.read()
        dataInputStream.readDouble();
    }

}