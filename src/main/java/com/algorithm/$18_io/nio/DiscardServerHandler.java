//package com.algorithm.$18_io.nio;
//
//import java.nio.channels.Channel;
//
//@ChannelPipelineCoverage("all")
//public class DiscardServerHandler extends SimpleChannelHandler {
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
//        e.getCause().printStackTrace();
//        Channel ch = e.getChannel();
//        ch.close();
//    }
//}