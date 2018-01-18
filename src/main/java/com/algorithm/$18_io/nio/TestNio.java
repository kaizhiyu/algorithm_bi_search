package com.algorithm.$18_io.nio;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @auth v_fanhaibo on   2018/1/18
 */
@SearchKeyWord("美团点评技术团队 nio selector")
//https://tech.meituan.com/nio.html
public class TestNio {

    public static void main(String[] args) throws Exception {

//        OutputStream outputStream = new FileOutputStream("").getChannel();
//        HttpServletRequest httpServletRequest = new HttpServletRequest();
//        httpServletRequest.getInputStream()
        //TLAB

        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            // select()阻塞，等待有事件发生唤醒
            int selected = selector.select();

            if (selected > 0) {
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    if (key.isAcceptable()) {
//                            SelectableChannel channel = key.channel();
                        // 处理 accept 事件
                    } else if (key.isReadable()) {
                        // 处理 read 事件
                    } else if (key.isWritable()) {
                        // 处理 write 事件
                    }
                    selectedKeys.remove();
                }
            }
        }
    }

}
