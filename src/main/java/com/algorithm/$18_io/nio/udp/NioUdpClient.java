package com.algorithm.$18_io.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class NioUdpClient {
    private DatagramChannel channel;
    private String ip = "127.0.0.1";
    private int port = 8000;
    private Selector selector;

    public void initUdpClient() throws IOException {
        channel = DatagramChannel.open();
        selector = Selector.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(ip, port));
        //有个现象：  
        //两个控制台君出现不接受数据也不发送数据，仅仅启动了两端  
        // new nio client start   
        // new nio server start  
        //原因猜测：注册任意事件都是与通道相关的，但是，很明显当客户端启动时，通道的各种就绪状态还没出现，所以需要手动  
        //channel.write(ByteBuffer.wrap("client send msg".getBytes()));  
        //来引发服务器端通道相关的就绪状态  
        channel.write(ByteBuffer.wrap("client send msg".getBytes()));
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void listen() throws IOException {
        System.out.println("new nio client start");
        while (true) {
            selector.select();
            System.out.println("new udp client channel join");
            Iterator<?> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();
                handler(key);
            }
        }
    }

    public void handler(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            handlerRead(key);
        }
    }


    public void handlerRead(SelectionKey key) throws IOException {


        DatagramChannel channel = (DatagramChannel) key.channel();

        //接受来自服务端的数据报文  
        ByteBuffer buf = ByteBuffer.allocate(65500);
        buf.clear();
        channel.receive(buf);
        buf.flip();
        String content = "";
        while (buf.hasRemaining()) {
            buf.get(new byte[buf.limit()]);
            content += new String(buf.array());
        }
        buf.clear();
        System.out.println("udp client content :" + content);

    }

    public void close() throws IOException {
        this.channel.close();
        this.selector.close();
    }


    public static void main(String[] args) throws IOException {
        NioUdpClient client = new NioUdpClient();
        client.initUdpClient();
        client.listen();
        //client.close();  
    }
} 