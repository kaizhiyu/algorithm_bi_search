package com.algorithm.$18_io.nio.mynio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @auth v_fanhaibo on   2018/1/18
 */
public class MyNioServer_van {

    public static void main(String[] args) throws IOException {
        String encoding = System.getProperty("file.encoding");

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(8090));
        serverSocketChannel.configureBlocking(false);
        int OP_ACCEPT = serverSocketChannel.validOps();
        serverSocketChannel.register(selector, OP_ACCEPT,null);

        while (true){
            int select = selector.select();//连接
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if (next.isWritable()) {

                    }
                    if (next.isReadable()) {
                        SocketChannel socketChannel =(SocketChannel) next.channel();
                        allocate.clear();
                        socketChannel.read(allocate);
                        allocate.flip();
                        String str = Charset.forName(encoding).decode(allocate).toString();
                        System.out.println("==="+ str);
                        if ("end$".equals(str)){
                            socketChannel.close();
                        }
                    }
                    iterator.remove();
                }


            }

        }
    }


}
