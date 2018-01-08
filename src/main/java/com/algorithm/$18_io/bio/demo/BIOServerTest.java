package com.algorithm.$18_io.bio.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/8.
 * @version:v1.0
 */

public class BIOServerTest {

    public static void main(String[] args) throws IOException, InterruptedException {


        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(8088));
        while (true){
            Socket accept = ss.accept();
            InputStream in = accept.getInputStream();
            byte[] bytes = new byte[1024];
            in.read(bytes);
            String receiveStr = new String(bytes);
            System.out.println(receiveStr);
            OutputStream outputStream = accept.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write("i have receive your message, "+receiveStr);
            bw.write("over.");
            bw.flush();
            in.close();
            bw.close();

//            Thread.sleep(1000L);

        }
    }
}
