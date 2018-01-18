package com.algorithm.$18_io.nio;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.net.Socket;

/**
 * @auth v_fanhaibo on   2018/1/18
 */

/** nio Reactor 通知可以读写了 */
/** nio Proactor 通知读写就绪了 ？？ */

@SearchKeyWord("Reactor Proactor  美团demo") //https://tech.meituan.com/nio.html
public class Reactor_demo {

    interface ChannelHandler{
        void channelReadable(Channel channel);
        void channelWritable(Channel channel);
    }
    class Channel{
        Socket socket;
//        Event event;//读，写或者连接
    }

    //IO线程主循环:
    class IoThread extends Thread{
        public void run(){
//            Channel channel;
//            while(channel= Selector.select()){//选择就绪的事件和对应的连接
//                if(channel.event==accept){
//                    registerNewChannelHandler(channel);//如果是新连接，则注册一个新的读写处理器
//                }
//                if(channel.event==write){
//                    getChannelHandler(channel).channelWritable(channel);//如果可以写，则执行写事件
//                }
//                if(channel.event==read){
//                    getChannelHandler(channel).channelReadable(channel);//如果可以读，则执行读事件
//                }
//            }
        }
//        Map<Channel，ChannelHandler> handlerMap;//所有channel的对应事件处理器
    }
}
