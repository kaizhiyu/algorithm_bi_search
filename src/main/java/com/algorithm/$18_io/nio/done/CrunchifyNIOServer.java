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
 * @author Crunchify.com
 */

public class CrunchifyNIOServer {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
        socketChannel.bind(crunchifyAddr);
        socketChannel.configureBlocking(false);

        int ops = socketChannel.validOps();
        socketChannel.register(selector, ops, null);
        while (true) {
            System.out.println("======================");
            int select = selector.select();
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();

            while ( select > 0 && crunchifyIterator.hasNext()) {
                SelectionKey myKey = crunchifyIterator.next();
                if (myKey.isAcceptable()) {
                    SocketChannel crunchifyClient = socketChannel.accept();
                    crunchifyClient.configureBlocking(false);
                    crunchifyClient.register(selector, SelectionKey.OP_READ);
                } else if (myKey.isReadable()) {

                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                    crunchifyClient.read(crunchifyBuffer);
                    String result = new String(crunchifyBuffer.array()).trim();
                    System.out.println("accept " + result);
                    if (result.equals("Crunchify")) {
                        crunchifyClient.close();
                    }
                }
                crunchifyIterator.remove();
            }
        }
    }

    private static void log(String str) {
        System.out.println(str);
    }
}