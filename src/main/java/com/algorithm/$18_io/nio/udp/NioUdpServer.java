package com.algorithm.$18_io.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;


public class NioUdpServer {
    //http://blog.csdn.net/foart/article/details/47608475  
    //http://www.cnblogs.com/rainy-shurun/p/5756501.html  
    //这个要多琢磨琢磨  
    private int port = 8000;
    private DatagramChannel channel;
    private Selector selector = null;

    public void initNioUdpServer() throws IOException {
        //创建udp连接通道  
        channel = DatagramChannel.open();
        //创建通道管理器  
        selector = Selector.open();
        //设置通道为非阻塞性通道（不会创建线程）  
        channel.configureBlocking(false);
        //将该通道绑定对应的端口  
        channel.socket().bind(new InetSocketAddress(port));
        //为通道注册通道管理器，并且注册一个可读就绪事件  
        channel.register(selector, SelectionKey.OP_READ);
    }

    //启动轮询来接受来自客户端的数据报文  
    public void listen() throws IOException {
        System.out.println("new nio server start");
        while (true) {
            //有通道就绪时（即select()的返回值大于0时），不阻塞，否则，阻塞，不会有任何的通道加入  
            selector.select();
            System.out.println("new udp server channel join");

            Iterator<?> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();//此处负责把udp连接断掉  
                clientHandler(key); //处理来自客户端的连接（通道的各种状态）  
            }
        }
    }

    private void clientHandler(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            handlerRead(key);
        }
    }


    //udp的接受客户端数据和响应的过程  
    private void handlerRead(SelectionKey key) throws IOException {

        //接受数据过程  
        DatagramChannel channel = (DatagramChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();//固定写法  
        InetSocketAddress address = (InetSocketAddress) channel.receive(buf);//接受来自客户端通道的数据
        buf.flip();//固定写法  
        String content = "";
        while (buf.hasRemaining()) {
            buf.get(new byte[buf.limit()]);
            content += new String(buf.array());
        }
        System.out.println("udp server content：" + content);
        buf.clear();

        //发送数据过程  
        ByteBuffer buf2 = ByteBuffer.allocate(65500);
        buf2.clear();
        buf2.put("udp server put data process!!".getBytes());
        buf2.flip();
        this.channel.send(buf2, address);

    }

    public void close() throws IOException {
        this.channel.close();
        this.selector.close();
    }


    public static void main(String[] args) throws IOException {
        NioUdpServer server = new NioUdpServer();
        server.initNioUdpServer();
        server.listen();
        //server.close();  
    }

} 