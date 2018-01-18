package com.algorithm.$18_io.nio.thinking;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fanhb on 2018/1/17
 * @desc xxxx
 */
public class ThinkingInJava_551 {

    /**  */
    private static final int SIZE = 1024;

    public static void main(String[] args) throws IOException {
        //write file
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();

        // add to the end of file :
        fc = new RandomAccessFile("data.txt","rw").getChannel();

        fc.position(fc.size() + 1);
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();

        //Read the file
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(SIZE);
        fc.read(buff);
        buff.flip();
        StringBuilder sb = new StringBuilder("");
        while (buff.hasRemaining())
//            System.out.println((char)buff.get());
            sb.append((char)buff.get());

        System.out.println(sb.toString());
        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap(sb.toString().getBytes()));
        fc.close();


    }

}
