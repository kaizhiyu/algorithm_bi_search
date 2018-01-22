package com.algorithm.$18_io.nio.done;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @auth v_fanhaibo on   2018/1/22
 */
public class NioServerDemo {

    ServerSocketChannel serverSocketChannel;

    public Selector open(int port) throws IOException {
        Selector selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", port);
        serverSocketChannel.bind(addr);
        serverSocketChannel.configureBlocking(false);
        //即：把ServerSocket 注册到selector上面
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    SocketChannel accept;
    ByteBuffer allocate = ByteBuffer.allocate(1024);
    StringBuffer strBuffer = new StringBuffer();

    public void listen(Selector selector) throws IOException {
        int j = 0;
        while (true) {
            //blocking here
            if (j==0)
                System.out.println("selector is waiting....0");
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isValid() && next.isAcceptable()) {
                        accept = serverSocketChannel.accept();
//                        Socket socket = accept.socket();
                        //即：把socket 注册到selector上面
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    }
                    if (next.isValid() && next.isReadable()) {

                        allocate.clear();
                        int read = 0;
                        try {
                            read = accept.read(allocate);
                        } catch (IOException e) {
//                            e.printStackTrace();
                        }
                        allocate.flip();
                        if (read > 0) {
                            String receivedMsg = new String(allocate.array());
                            strBuffer.append(receivedMsg);
                            System.out.println(" server:  " + receivedMsg + ",第-" + j++ + "-次");
                        } else {
                            accept.close();
                        }
                        //不关闭socket，selector会一直轮询


//                        accept.register(selector,SelectionKey.OP_WRITE);

                    }
//                    if (next.isValid() && next.isWritable()) {
//                        accept.write(ByteBuffer.wrap("server has received your msg!!!".getBytes()));
//                    }
                    iterator.remove();

                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        NioServerDemo nioServerDemo = new NioServerDemo();
        Selector selector = nioServerDemo.open(8080);
        nioServerDemo.listen(selector);


    }

}
