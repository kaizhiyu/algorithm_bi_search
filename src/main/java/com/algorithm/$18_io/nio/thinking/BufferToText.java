package com.algorithm.$18_io.nio.thinking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author fanhb on 2018/1/17
 * @desc xxxx
 */
public class BufferToText {

    public static void main(String[] args) throws IOException {


        FileChannel channel = new FileOutputStream("data.txt").getChannel();
        channel.write(ByteBuffer.wrap("data".getBytes()));
        channel.close();

        FileChannel channel1 = new FileInputStream("data.txt").getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        channel1.read(allocate);
        channel1.close();
        allocate.flip();

        //Doesn't work:
        System.out.println("doesn't work = "+allocate.asCharBuffer());
        //Decode using this system's default Charset
        String encoding = System.getProperty("file.encoding");

        System.out.println("encoding=" + encoding + ": " + Charset.forName(encoding).decode(allocate));

        // Or we could encode with something that will print
        FileChannel channel2 = new FileOutputStream("data.txt").getChannel();
        channel2.write(ByteBuffer.wrap("some more text ".getBytes("UTF-16BE")));
        channel2.close();

        //now try reading again
        FileChannel channel3 = new FileInputStream("data.txt").getChannel();
        allocate.clear();
        channel3.read(allocate);
        allocate.flip();
        channel3.close();

        System.out.println("channel3 = "+ allocate.asCharBuffer());

        // Use a charBuffer to write through:
        FileChannel channel4 = new FileOutputStream("data.txt").getChannel();

        ByteBuffer allocate1 = ByteBuffer.allocate(2048); // more than needed
        allocate1.clear();
        allocate1.asCharBuffer().put("some allocate1");
        channel4.write(allocate1);
        allocate1.flip();
        channel4.close();

        //Read and display
        FileChannel channel5 = new FileInputStream("data.txt").getChannel();
        ByteBuffer allocate2 = ByteBuffer.allocate(1024);
        allocate2.clear(); //prepare for read
        channel5.read(allocate2);
        allocate2.flip(); // prepare for write
        channel5.close();
        System.out.println("channel5 = "+allocate2.asCharBuffer());

//        printnb
        //now try reading again
        FileChannel channel6 = new FileInputStream("data.txt").getChannel();
        ByteBuffer allocate3 = ByteBuffer.allocate(1024);
        allocate3.clear();
        channel6.read(allocate3);
        allocate3.flip();
        channel6.close();
        System.out.println("channel6 = " + allocate3.asCharBuffer());

        //now try reading again
        FileChannel channel7 = new FileInputStream("data.txt").getChannel();
        allocate.clear();
        channel7.read(allocate);
        allocate.flip();
        channel7.close();

        System.out.println("channel7 = "+ allocate.asCharBuffer());
    }
}
