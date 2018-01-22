package com.algorithm.$18_io.nio.done;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth v_fanhaibo on   2018/1/22
 */
public class NioClientDemo {

    public void open() throws IOException, InterruptedException {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            List<String> msg = new ArrayList<>();
            msg.add(" demo ==");
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
            socketChannel.configureBlocking(false);
            for (String s : msg) {
                s = i + s;
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                allocate.put(s.getBytes());
                allocate.flip();
                socketChannel.write(ByteBuffer.wrap(s.getBytes()));
//                Thread.sleep(2000L);
                System.out.println("j =  " + j++);
            }
//            Thread.sleep(2000L);
            socketChannel.close();
        }


//        socketChannel.close();


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new NioClientDemo().open();
    }

}
