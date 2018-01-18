package com.algorithm.$18_io.nio.thinking;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @auth v_fanhaibo on   2018/1/18
 */
@SearchKeyWord("byteBuffer demo")
public class ByteBuffTestDemo {

    public static void main(String[] args) throws IOException {

        String encoding = System.getProperty("file.encoding");

        // Use a charBuffer to write through:
        FileChannel channel1 = new FileOutputStream("data.txt").getChannel();

        ByteBuffer allocate1 = ByteBuffer.allocate(2048); // more than needed
        allocate1.clear();
        allocate1.asCharBuffer().put("some allocate1");
        channel1.write(allocate1);
        allocate1.flip();
        channel1.close();

        /** ================================================= */
        ByteBuffer allocateRet1 = ByteBuffer.allocate(1204);

        FileChannel result1 = new FileInputStream("data.txt").getChannel();
        allocateRet1.clear();
        result1.read(allocateRet1);
        allocateRet1.flip();
        result1.close();
        System.out.println(allocateRet1.asCharBuffer());
        System.out.println("encoding=" + encoding + ": " + Charset.forName(encoding).decode(allocateRet1));

        /** ================================================= */

        //    buf.put(magic);    // Prepend header
        //    in.read(buf);      // Read data into rest of buffer
        //    buf.flip();        // Flip buffer
        //    out.write(buf);    // Write header + data to channel

        FileChannel channel2 = new FileInputStream("data.txt").getChannel();
        FileChannel channel3 = new FileOutputStream("data3.txt").getChannel();
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//        buf.put("mm".getBytes());
//        channel2.read(buf);
//        buf.flip();
//        channel3.write(buf);
//        channel3.close();
//        channel2.close();
//        System.out.println("58 = " + Charset.forName(encoding).decode(buf));

        channel2.transferTo(0,1024,channel3);
        channel2.close();
        channel3.close();
        // result


        ByteBuffer allocateRet = ByteBuffer.allocate(1204);

        FileChannel result = new FileInputStream("data.txt").getChannel();
        allocateRet.clear();
        result.read(allocateRet);
        allocateRet.flip();
        result.close();
        System.out.println("encoding=" + encoding + ": " + Charset.forName(encoding).decode(allocateRet));

    }

}
