package com.algorithm.$18_io.bio.demo;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        while (true) {
            Socket accept = ss.accept();
            InputStream in = accept.getInputStream();
            byte[] bytes = new byte[1024];
            in.read(bytes);
            String receiveStr = new String(bytes);
            System.out.println(receiveStr);
            OutputStream outputStream = accept.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write("i have receive your message, " + receiveStr);
            bw.write("over.");
            bw.flush();
            in.close();
            bw.close();

//            Thread.sleep(1000L);

        }
    }


    @SearchKeyWord("美团demo")
    public void testBioMethod() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(100);//线程池

        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(8088));
        while (!Thread.currentThread().isInterrupted()) {//主线程死循环等待新连接到来
            Socket socket = serverSocket.accept();
            executor.submit(new ConnectIOnHandler(socket));//为新的连接创建新的线程
        }


    }

    class ConnectIOnHandler extends Thread {
        private Socket socket;

        public ConnectIOnHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
//                    socket.getReuseAddress()
                //死循环处理读写事件
//                    String someThing = socket.read()//读取数据
//                    if(someThing!=null){
                //处理数据
//                        socket.write();//写数据
//                    }

            }
        }
    }

}