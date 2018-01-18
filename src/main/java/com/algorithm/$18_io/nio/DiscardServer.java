//package com.algorithm.$18_io.nio;
//import org.apache.http.impl.bootstrap.ServerBootstrap;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//public class DiscardServer {
//    public static void main(String[] args) throws Exception {
//        ChannelFactory factory =
//            new NioServerSocketChannelFactory (
//                    Executors.newCachedThreadPool(),
//                    Executors.newCachedThreadPool());
//        ServerBootstrap bootstrap = new ServerBootstrap(factory);
//        DiscardServerHandler handler = new DiscardServerHandler();
//        ChannelPipeline pipeline = bootstrap.getPipeline();
//        pipeline.addLast("handler", handler);
//        bootstrap.setOption("child.tcpNoDelay", true);
//        bootstrap.setOption("child.keepAlive", true);
//        bootstrap.bind(new InetSocketAddress(8080));
//    }
//}