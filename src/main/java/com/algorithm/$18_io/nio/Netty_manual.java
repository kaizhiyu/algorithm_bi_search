package com.algorithm.$18_io.nio;

/**
 * @auth v_fanhaibo on   2018/1/18
 */
public class Netty_manual {

    /**
     *
     * Netty 3.1 中文用户手册



     下载：http://www.codepub.com/software/view-software-17736.html
     在线阅读：http://edu.codepub.com/2010/0413/21990.php


     The Netty Project 3.1 User Guide
     The Proven Approach to Rapid Network Application Development

     3.1.5.GA, r1772

     序言    1
     1. 问题    1
     2. 方案    2
     第一章. 开始    2
     1.1. 开始之前    3
     1.2. 抛弃协议服务    3
     1.3. 查看接收到的数据    5
     1.4. 响应协议服务    6
     1.5. 时间协议服务    7
     1.6. 时间协议服务客户端    9
     1.7. 流数据的传输处理    11
     1.7.1. Socket Buffer的缺陷    11
     1.7.2. 第一种方案    11
     1.7.3. 第二种方案    13
     1.8. 使用POJO代替ChannelBuffer    15
     1.9. 关闭你的应用    18
     1.10. 总述    21
     第二章. 架构总览    22
     2.1. 丰富的缓冲实现    22
     2.2. 统一的异步 I/O API    22
     2.3. 基于拦截链模式的事件模型    23
     2.4. 适用快速开发的高级组件    24
     2.4.1. Codec框架    24
     2.4.2. SSL / TLS 支持    25
     2.4.3. HTTP实现    25
     2.4.4. Google Protocol Buffer 整合    25
     2.5. 总述    26



     序言

     本指南对Netty 进行了介绍并指出其意义所在。

     1. 问题

     现在，我们使用适合一般用途的应用或组件来和彼此通信。例如，我们常常使用一个HTTP客户端从远程服务器获取信息或者通过web services进行远程方法的调用。

     然而，一个适合普通目的的协议或其实现并不具备其规模上的扩展性。例如，我们无法使用一个普通的HTTP服务器进行大型文件，电邮信息的交互，或者处理金融信息和多人游戏数据那种要求准实时消息传递的应用场景。因此，这些都要求使用一个适用于特殊目的并经过高度优化的协议实现。例如，你可能想要实现一个对基于AJAX的聊天应用，媒体流或大文件传输进行过特殊优化的HTTP服务器。你甚至可能想去设计和实现一个全新的，特定于你的需求的通信协议。

     另一种无法避免的场景是你可能不得不使用一种专有的协议和原有系统交互。在这种情况下，你需要考虑的是如何能够快速的开发出这个协议的实现并且同时还没有牺牲最终应用的性能和稳定性。

     2. 方案

     Netty 是一个异步的，事件驱动的网络编程框架和工具，使用Netty 可以快速开发出可维护的，高性能、高扩展能力的协议服务及其客户端应用。

     也就是说，Netty 是一个基于NIO的客户，服务器端编程框架，使用Netty 可以确保你快速和简单的开发出一个网络应用，例如实现了某种协议的客户，服务端应用。Netty相当简化和流线化了网络应用的编程开发过程，例如，TCP和UDP的socket服务开发。

     “快速”和“简单”并不意味着会让你的最终应用产生维护性或性能上的问题。Netty 是一个吸收了多种协议的实现经验，这些协议包括FTP,SMPT,HTTP，各种二进制，文本协议，并经过相当精心设计的项目，最终，Netty 成功的找到了一种方式，在保证易于开发的同时还保证了其应用的性能，稳定性和伸缩性。

     一些用户可能找到了某些同样声称具有这些特性的编程框架，因此你们可能想问Netty 又有什么不一样的地方。这个问题的答案是Netty 项目的设计哲学。从创立之初，无论是在API还是在其实现上Netty 都致力于为你提供最为舒适的使用体验。虽然这并不是显而易见的，但你终将会认识到这种设计哲学将令你在阅读本指南和使用Netty 时变得更加得轻松和容易。
     第一章. 开始

     这一章节将围绕Netty的核心结构展开，同时通过一些简单的例子可以让你更快的了解Netty的使用。当你读完本章，你将有能力使用Netty完成客户端和服务端的开发。

     如果你更喜欢自上而下式的学习方式，你可以首先完成 第二章：架构总览 的学习，然后再回到这里。

     1.1. 开始之前

     运行本章示例程序的两个最低要求是：最新版本的Netty程序以及JDK 1.5或更高版本。最新版本的Netty程序可在项目下载页 下载。下载正确版本的JDK，请到你偏好的JDK站点下载。

     这就已经足够了吗？实际上你会发现，这两个条件已经足够你完成任何协议的开发了。如果不是这样，请联系Netty项目社区 ，让我们知道还缺少了什么。

     最终但不是至少，当你想了解本章所介绍的类的更多信息时请参考API手册。为方便你的使用，这篇文档中所有的类名均连接至在线API手册。此外，如果本篇文档中有任何错误信息，无论是语法错误，还是打印排版错误或者你有更好的建议，请不要顾虑，立即联系Netty项目社区 。

     1.2. 抛弃协议服务

     在这个世界上最简化的协议不是“Hello,world!”而是抛弃协议 。这是一种丢弃接收到的任何数据并不做任何回应的协议。

     实现抛弃协议（DISCARD protocol），你仅需要忽略接受到的任何数据即可。让我们直接从处理器（handler）实现开始，这个处理器处理Netty的所有I/O事件。
     Java代码 
     1	package org.jboss.netty.example.discard;
     2
     3	@ChannelPipelineCoverage("all")
     4	public class DiscardServerHandler extends SimpleChannelHandler {
     5
     6	    @Override
     7	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     8	    }
     9
     10	    @Override
     11	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
     12	        e.getCause().printStackTrace();
     13
     14	        Channel ch = e.getChannel();
     15	        ch.close();
     16	    }
     17	}

     代码说明
     1)ChannelPipelineCoverage注解了一种处理器类型，这个注解标示了一个处理器是否可被多个Channel通道共享（同时关联着ChannelPipeline）。DiscardServerHandler没有处理任何有状态的信息，因此这里的注解是“all”。

     2)DiscardServerHandler继承了SimpleChannelHandler，这也是一个ChannelHandler 的实现。SimpleChannelHandler提供了多种你可以重写的事件处理方法。目前直接继承SimpleChannelHandler已经足够了，并不需要你完成一个自己的处理器接口。

     3)我们这里重写了messageReceived事件处理方法。这个方法由一个接收了客户端传送数据的MessageEvent事件调用。在这个例子中，我们忽略接收到的任何数据，并以此来实现一个抛弃协议（DISCARD protocol）。

     4)exceptionCaught 事件处理方法由一个ExceptionEvent异常事件调用，这个异常事件起因于Netty的I/O异常或一个处理器实现的内部异常。多数情况下，捕捉到的异常应当被记录下来，并在这个方法中关闭这个channel通道。当然处理这种异常情况的方法实现可能因你的实际需求而有所不同，例如，在关闭这个连接之前你可能会发送一个包含了错误码的响应消息。

     目前进展不错，我们已经完成了抛弃协议服务器的一半开发工作。下面要做的是完成一个可以启动这个包含DiscardServerHandler处理器服务的主方法。

     Java代码 
     18	package org.jboss.netty.example.discard;
     19
     20	import java.net.InetSocketAddress;
     21	import java.util.concurrent.Executors;
     22
     23	public class DiscardServer {
     24
     25	    public static void main(String[] args) throws Exception {
     26	        ChannelFactory factory =
     27	            new NioServerSocketChannelFactory (
     28	                    Executors.newCachedThreadPool(),
     29	                    Executors.newCachedThreadPool());
     30
     31	        ServerBootstrap bootstrap = new ServerBootstrap (factory);
     32
     33	        DiscardServerHandler handler = new DiscardServerHandler();
     34	        ChannelPipeline pipeline = bootstrap.getPipeline();
     35	        pipeline.addLast("handler", handler);
     36
     37	        bootstrap.setOption("child.tcpNoDelay", true);
     38	        bootstrap.setOption("child.keepAlive", true);
     39
     40	        bootstrap.bind(new InetSocketAddress(8080));
     41	    }
     42	}

     代码说明

     1)ChannelFactory 是一个创建和管理Channel通道及其相关资源的工厂接口，它处理所有的I/O请求并产生相应的I/O ChannelEvent通道事件。Netty 提供了多种 ChannelFactory 实现。这里我们需要实现一个服务端的例子，因此我们使用NioServerSocketChannelFactory实现。另一件需要注意的事情是这个工厂并自己不负责创建I/O线程。你应当在其构造器中指定该工厂使用的线程池，这样做的好处是你获得了更高的控制力来管理你的应用环境中使用的线程，例如一个包含了安全管理的应用服务。

     2)ServerBootstrap 是一个设置服务的帮助类。你甚至可以在这个服务中直接设置一个Channel通道。然而请注意，这是一个繁琐的过程，大多数情况下并不需要这样做。

     3)这里，我们将DiscardServerHandler处理器添加至默认的ChannelPipeline通道。任何时候当服务器接收到一个新的连接，一个新的ChannelPipeline管道对象将被创建，并且所有在这里添加的ChannelHandler对象将被添加至这个新的ChannelPipeline管道对象。这很像是一种浅拷贝操作（a shallow-copy operation）；所有的Channel通道以及其对应的ChannelPipeline实例将分享相同的DiscardServerHandler实例。

     4)你也可以设置我们在这里指定的这个通道实现的配置参数。我们正在写的是一个TCP/IP服务，因此我们运行设定一些socket选项，例如tcpNoDelay和keepAlive。请注意我们在配置选项里添加的"child."前缀。这意味着这个配置项仅适用于我们接收到的通道实例，而不是ServerSocketChannel实例。因此，你可以这样给一个ServerSocketChannel设定参数：
     bootstrap.setOption("reuseAddress", true);

     5)我们继续。剩下要做的是绑定这个服务使用的端口并且启动这个服务。这里，我们绑定本机所有网卡（NICs,network interface cards）上的8080端口。当然，你现在也可以对应不同的绑定地址多次调用绑定操作。


     大功告成！现在你已经完成你的第一个基于Netty的服务端程序。

     1.3. 查看接收到的数据

     现在你已经完成了你的第一个服务端程序，我们需要测试它是否可以真正的工作。最简单的方法是使用telnet 命令。例如，你可以在命令行中输入“telnet localhost 8080 ”或其他类型参数。

     然而，我们可以认为服务器在正常工作吗？由于这是一个丢球协议服务，所以实际上我们无法真正的知道。你最终将收不到任何回应。为了证明它在真正的工作，让我们修改代码打印其接收到的数据。
     我们已经知道当完成数据的接收后将产生MessageEvent消息事件，并且也会触发messageReceived处理方法。所以让我在DiscardServerHandler处理器的messageReceived方法内增加一些代码。
     Java代码 
     43	@Override
     44	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     45	    ChannelBuffer  buf = (ChannelBuffer) e.getMessage();
     46	    while(buf.readable()) {
     47	        System.out.println((char) buf.readByte());
     48	    }
     49	}

     代码说明
     1) 基本上我们可以假定在socket的传输中消息类型总是ChannelBuffer。ChannelBuffer是Netty的一个基本数据结构，这个数据结构存储了一个字节序列。ChannelBuffer类似于NIO的ByteBuffer，但是前者却更加的灵活和易于使用。例如，Netty允许你创建一个由多个ChannelBuffer构建的复合ChannelBuffer类型，这样就可以减少不必要的内存拷贝次数。

     2) 虽然ChannelBuffer有些类似于NIO的ByteBuffer，但强烈建议你参考Netty的API手册。学会如何正确的使用ChannelBuffer是无障碍使用Netty的关键一步。

     如果你再次运行telnet命令，你将会看到你所接收到的数据。
     抛弃协议服务的所有源代码均存放在在分发版的org.jboss.netty.example.discard包下。

     1.4. 响应协议服务

     目前，我们虽然使用了数据，但最终却未作任何回应。然而一般情况下，一个服务都需要回应一个请求。让我们实现ECHO协议 来学习如何完成一个客户请求的回应消息，ECHO协议规定要返回任何接收到的数据。

     与我们上一节实现的抛弃协议服务唯一不同的地方是，这里需要返回所有的接收数据而不是仅仅打印在控制台之上。因此我们再次修改messageReceived方法就足够了。
     Java代码 
     50	@Override
     51	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     52	    Channel  ch = e.getChannel();
     53	    ch.write(e.getMessage());
     54	}
     代码说明
     1) 一个ChannelEvent通道事件对象自身存有一个和其关联的Channel对象引用。这个返回的Channel通道对象代表了这个接收 MessageEvent消息事件的连接（connection）。因此，我们可以通过调用这个Channel通道对象的write方法向远程节点写入返回数据。


     现在如果你再次运行telnet命令，你将会看到服务器返回的你所发送的任何数据。

     相应服务的所有源代码存放在分发版的org.jboss.netty.example.echo包下。

     1.5. 时间协议服务

     这一节需要实现的协议是TIME协议 。这是一个与先前所介绍的不同的例子。这个例子里，服务端返回一个32位的整数消息，我们不接受请求中包含的任何数据并且当消息返回完毕后立即关闭连接。通过这个例子你将学会如何构建和发送消息，以及当完成处理后如何主动关闭连接。

     因为我们会忽略接收到的任何数据而只是返回消息，这应当在建立连接后就立即开始。因此这次我们不再使用messageReceived方法，取而代之的是使用channelConnected方法。下面是具体的实现：

     Java代码 
     55	package org.jboss.netty.example.time;
     56
     57	@ChannelPipelineCoverage("all")
     58	public class TimeServerHandler extends SimpleChannelHandler {
     59
     60	    @Override
     61	    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
     62	        Channel ch = e.getChannel();
     63
     64	        ChannelBuffer time = ChannelBuffers.buffer(4);
     65	        time.writeInt(System.currentTimeMillis() / 1000);
     66
     67	        ChannelFuture f = ch.write(time);
     68
     69	        f.addListener(new ChannelFutureListener() {
     70	            public void operationComplete(ChannelFuture future) {
     71	                Channel ch = future.getChannel();
     72	                ch.close();
     73	            }
     74	        });
     75	    }
     76
     77	    @Override
     78	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
     79	        e.getCause().printStackTrace();
     80	        e.getChannel().close();
     81	    }
     82	}

     代码说明
     1) 正如我们解释过的，channelConnected方法将在一个连接建立后立即触发。因此让我们在这个方法里完成一个代表当前时间（秒）的32位整数消息的构建工作。

     2) 为了发送一个消息，我们需要分配一个包含了这个消息的buffer缓冲。因为我们将要写入一个32位的整数，因此我们需要一个4字节的 ChannelBuffer。ChannelBuffers是一个可以创建buffer缓冲的帮助类。除了这个buffer方法，ChannelBuffers还提供了很多和ChannelBuffer相关的实用方法。更多信息请参考API手册。

     另外，一个很不错的方法是使用静态的导入方式：
     import static org.jboss.netty.buffer.ChannelBuffers.*;
     ...
     ChannelBuffer dynamicBuf = dynamicBuffer(256);
     ChannelBuffer ordinaryBuf = buffer(1024);

     3) 像通常一样，我们需要自己构造消息。

     但是打住，flip在哪？过去我们在使用NIO发送消息时不是常常需要调用 ByteBuffer.flip()方法吗？实际上ChannelBuffer之所以不需要这个方法是因为 ChannelBuffer有两个指针；一个对应读操作，一个对应写操作。当你向一个 ChannelBuffer写入数据的时候写指针的索引值便会增加，但与此同时读指针的索引值不会有任何变化。读写指针的索引值分别代表了这个消息的开始、结束位置。

     与之相应的是，NIO的buffer缓冲没有为我们提供如此简洁的一种方法，除非你调用它的flip方法。因此，当你忘记调用flip方法而引起发送错误时，你便会陷入困境。这样的错误不会再Netty中发生，因为我们对应不同的操作类型有不同的指针。你会发现就像你已习惯的这样过程变得更加容易—一种没有flippling的体验！

     另一点需要注意的是这个写方法返回了一个ChannelFuture对象。一个ChannelFuture 对象代表了一个尚未发生的I/O操作。这意味着，任何已请求的操作都可能是没有被立即执行的，因为在Netty内部所有的操作都是异步的。例如，下面的代码可能会关闭一 个连接，这个操作甚至会发生在消息发送之前：

     Channel ch = ...;
     ch.write(message);
     ch.close();

     因此，你需要这个write方法返回的ChannelFuture对象，close方法需要等待写操作异步完成之后的ChannelFuture通知/监听触发。需要注意的是，关闭方法仍旧不是立即关闭一个连接，它同样也是返回了一个ChannelFuture对象。

     4) 在写操作完成之后我们又如何得到通知？这个只需要简单的为这个返回的ChannelFuture对象增加一个ChannelFutureListener 即可。在这里我们创建了一个匿名ChannelFutureListener对象，在这个ChannelFutureListener对象内部我们处理了异步操作完成之后的关闭操作。

     另外，你也可以通过使用一个预定义的监听类来简化代码。
     f.addListener(ChannelFutureListener.CLOSE);



     1.6. 时间协议服务客户端

     不同于DISCARD和ECHO协议服务，我们需要一个时间协议服务的客户端，因为人们无法直接将一个32位的二进制数据转换一个日历时间。在这一节我们将学习如何确保服务器端工作正常，以及如何使用Netty完成客户端的开发。

     使用Netty开发服务器端和客户端代码最大的不同是要求使用不同的Bootstrap及ChannelFactory。请参照以下的代码：
     Java代码 
     83	package org.jboss.netty.example.time;
     84
     85	import java.net.InetSocketAddress;
     86	import java.util.concurrent.Executors;
     87
     88	public class TimeClient {
     89
     90	    public static void main(String[] args) throws Exception {
     91	        String host = args[0];
     92	        int port = Integer.parseInt(args[1]);
     93
     94	        ChannelFactory factory =
     95	            new NioClientSocketChannelFactory (
     96	                    Executors.newCachedThreadPool(),
     97	                    Executors.newCachedThreadPool());
     98
     99	        ClientBootstrap bootstrap = new ClientBootstrap (factory);
     100
     101	        TimeClientHandler handler = new TimeClientHandler();
     102	        bootstrap.getPipeline().addLast("handler", handler);
     103
     104	        bootstrap.setOption("tcpNoDelay" , true);
     105	        bootstrap.setOption("keepAlive", true);
     106
     107	        bootstrap.connect (new InetSocketAddress(host, port));
     108	    }
     109	}
     代码说明
     1) 使用NioClientSocketChannelFactory而不是NioServerSocketChannelFactory来创建客户端的Channel通道对象。

     2) 客户端的ClientBootstrap对应ServerBootstrap。

     3) 请注意，这里不存在使用“child.”前缀的配置项，客户端的SocketChannel实例不存在父级Channel对象。

     4) 我们应当调用connect连接方法，而不是之前的bind绑定方法。

     正如你所看到的，这与服务端的启动过程是完全不一样的。ChannelHandler又该如何实现呢？它应当负责接收一个32位的整数，将其转换为可读的格式后，打印输出时间，并关闭这个连接。

     Java代码 
     110	package org.jboss.netty.example.time;
     111
     112	import java.util.Date;
     113
     114	@ChannelPipelineCoverage("all")
     115	public class TimeClientHandler extends SimpleChannelHandler {
     116
     117	    @Override
     118	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     119	        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
     120	        long currentTimeMillis = buf.readInt() * 1000L;
     121	        System.out.println(new Date(currentTimeMillis));
     122	        e.getChannel().close();
     123	    }
     124
     125	    @Override
     126	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
     127	        e.getCause().printStackTrace();
     128	        e.getChannel().close();
     129	    }
     130	}

     这看起来很是简单，与服务端的实现也并未有什么不同。然而，这个处理器却时常会因为抛出IndexOutOfBoundsException异常而拒绝工作。我们将在下一节讨论这个问题产生的原因。

     1.7. 流数据的传输处理

     1.7.1. Socket Buffer的缺陷

     对于例如TCP/IP这种基于流的传输协议实现，接收到的数据会被存储在socket的接受缓冲区内。不幸的是，这种基于流的传输缓冲区并不是一个包队列，而是一个字节队列。这意味着，即使你以两个数据包的形式发送了两条消息，操作系统却不会把它们看成是两条消息，而仅仅是一个批次的字节序列。因此，在这种情况下我们就无法保证收到的数据恰好就是远程节点所发送的数据。例如，让我们假设一个操作系统的TCP/IP堆栈收到了三个数据包：

     +-----+-----+-----+
     | ABC | DEF | GHI |
     +-----+-----+-----+

     由于这种流传输协议的普遍性质，在你的应用中有较高的可能会把这些数据读取为另外一种形式：

     +----+-------+---+---+
     | AB | CDEFG | H | I |
     +----+-------+---+---+

     因此对于数据的接收方，不管是服务端还是客户端，应当重构这些接收到的数据，让其变成一种可让你的应用逻辑易于理解的更有意义的数据结构。在上面所述的这个例子中，接收到的数据应当重构为下面的形式：

     +-----+-----+-----+
     | ABC | DEF | GHI |
     +-----+-----+-----+

     1.7.2. 第一种方案

     现在让我们回到时间协议服务客户端的例子中。我们在这里遇到了同样的问题。一个32位的整数是一个非常小的数据量，因此它常常不会被切分在不同的数据段内。然而，问题是它确实可以被切分在不同的数据段内，并且这种可能性随着流量的增加而提高。

     最简单的方案是在程序内部创建一个可准确接收4字节数据的累积性缓冲。下面的代码是修复了这个问题后的TimeClientHandler实现。

     Java代码 
     131	package org.jboss.netty.example.time;
     132
     133	import static org.jboss.netty.buffer.ChannelBuffers.*;
     134
     135	import java.util.Date;
     136
     137	@ChannelPipelineCoverage("one")
     138	public class TimeClientHandler extends SimpleChannelHandler {
     139
     140	    private final ChannelBuffer buf = dynamicBuffer();
     141
     142	    @Override
     143	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     144	        ChannelBuffer m = (ChannelBuffer) e.getMessage();
     145	        buf.writeBytes(m);
     146
     147	        if (buf.readableBytes() >= 4) {
     148	            long currentTimeMillis = buf.readInt() * 1000L;
     149	            System.out.println(new Date(currentTimeMillis));
     150	            e.getChannel().close();
     151	        }
     152	    }
     153
     154	    @Override
     155	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
     156	        e.getCause().printStackTrace();
     157	        e.getChannel().close();
     158	    }
     159	}

     代码说明
     1) 这一次我们使用“one”做为ChannelPipelineCoverage的注解值。这是由于这个修改后的TimeClientHandler不在不在内部保持一个buffer缓冲，因此这个TimeClientHandler实例不可以再被多个Channel通道或ChannelPipeline共享。否则这个内部的buffer缓冲将无法缓冲正确的数据内容。

     2) 动态的buffer缓冲也是ChannelBuffer的一种实现，其拥有动态增加缓冲容量的能力。当你无法预估消息的数据长度时，动态的buffer缓冲是一种很有用的缓冲结构。

     3) 首先，所有的数据将会被累积的缓冲至buf容器。

     4) 之后，这个处理器将会检查是否收到了足够的数据然后再进行真实的业务逻辑处理，在这个例子中需要接收4字节数据。否则，Netty将重复调用messageReceived方法，直至4字节数据接收完成。


     这里还有另一个地方需要进行修改。你是否还记得我们把TimeClientHandler实例添加到了这个ClientBootstrap实例的默认ChannelPipeline管道里？这意味着同一个TimeClientHandler实例将被多个Channel通道共享，因此接受的数据也将受到破坏。为了给每一个Channel通道创建一个新的TimeClientHandler实例，我们需要实现一个ChannelPipelineFactory管道工厂：
     Java代码
     160	package org.jboss.netty.example.time;
     161
     162	public class TimeClientPipelineFactory implements ChannelPipelineFactory {
     163
     164	    public ChannelPipeline getPipeline() {
     165	        ChannelPipeline pipeline = Channels.pipeline();
     166	        pipeline.addLast("handler", new TimeClientHandler());
     167	        return pipeline;
     168	    }
     169	}

     现在，我们需要把TimeClient下面的代码片段：
     Java代码 !
     170	TimeClientHandler handler = new TimeClientHandler();
     171	bootstrap.getPipeline().addLast("handler", handler);

     替换为：
     Java代码 "
     172	bootstrap.setPipelineFactory(new TimeClientPipelineFactory());

     虽然这看上去有些复杂，并且由于在TimeClient内部我们只创建了一个连接（connection），因此我们在这里确实没必要引入TimeClientPipelineFactory实例。

     然而，当你的应用变得越来越复杂，你就总会需要实现自己的ChannelPipelineFactory，这个管道工厂将会令你的管道配置变得更加具有灵活性。

     1.7.3. 第二种方案

     虽然第二种方案解决了时间协议客户端遇到的问题，但是这个修改后的处理器实现看上去却不再那么简洁。设想一种更为复杂的，由多个可变长度字段组成的协议。你的ChannelHandler实现将变得越来越难以维护。

     正如你已注意到的，你可以为一个ChannelPipeline添加多个ChannelHandler，因此，为了减小应用的复杂性，你可以把这个臃肿的ChannelHandler切分为多个独立的模块单元。例如，你可以把TimeClientHandler切分为两个独立的处理器：
     ·	  TimeDecoder，解决数据分段的问题。
     ·	  TimeClientHandler，原始版本的实现。
     幸运的是，Netty提供了一个可扩展的类，这个类可以直接拿过来使用帮你完成TimeDecoder的开发：
     Java代码 $
     173	package org.jboss.netty.example.time;
     174
     175
     176	public class TimeDecoder extends FrameDecoder {
     177
     178	    @Override
     179	    protected Object decode(
     180	            ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer)  {
     181
     182	        if (buffer.readableBytes() < 4) {
     183	            return null;
     184	        }
     185
     186	        return buffer.readBytes(4);
     187	    }
     188	}

     代码说明
     1) 这里不再需要使用ChannelPipelineCoverage的注解，因为FrameDecoder总是被注解为“one”。

     2) 当接收到新的数据后，FrameDecoder会调用decode方法，同时传入一个FrameDecoder内部持有的累积型buffer缓冲。

     3) 如果decode返回null值，这意味着还没有接收到足够的数据。当有足够数量的数据后FrameDecoder会再次调用decode方法。

     4) 如果decode方法返回一个非空值，这意味着decode方法已经成功完成一条信息的解码。FrameDecoder将丢弃这个内部的累计型缓冲。请注意你不需要对多条消息进行解码，FrameDecoder将保持对decode方法的调用，直到decode方法返回非空对象。


     如果你是一个勇于尝试的人，你或许应当使用ReplayingDecoder，ReplayingDecoder更加简化了解码的过程。为此你需要查看API手册获得更多的帮助信息。
     Java代码 &
     189	package org.jboss.netty.example.time;
     190
     191	public class TimeDecoder extends ReplayingDecoder<VoidEnum> {
     192
     193	    @Override
     194	    protected Object decode(
     195	            ChannelHandlerContext ctx, Channel channel,
     196	            ChannelBuffer buffer, VoidEnum state) {
     197
     198	        return buffer.readBytes(4);
     199	    }
     200	}

     此外，Netty还为你提供了一些可以直接使用的decoder实现，这些decoder实现不仅可以让你非常容易的实现大多数协议，并且还会帮你避免某些臃肿、难以维护的处理器实现。请参考下面的代码包获得更加详细的实例：
     ·	  org.jboss.netty.example.factorial for a binary protocol, and
     ·	  org.jboss.netty.example.telnet for a text line-based protocol
     1.8. 使用POJO代替ChannelBuffer

     目前为止所有的实例程序都是使用ChannelBuffer做为协议消息的原始数据结构。在这一节，我们将改进时间协议服务的客户/服务端实现，使用POJO 而不是ChannelBuffer做为协议消息的原始数据结构。

     在你的ChannelHandler实现中使用POJO的优势是很明显的；从你的ChannelHandler实现中分离从ChannelBuffer获取数据的代码，将有助于提高你的ChannelHandler实现的可维护性和可重用性。在时间协议服务的客户/服务端代码中，直接使用ChannelBuffer读取一个32位的整数并不是一个主要的问题。然而，你会发现，当你试图实现一个真实的协议的时候，这种代码上的分离是很有必要的。

     首先，让我们定义一个称之为UnixTime的新类型。
     Java代码 '
     201	package org.jboss.netty.example.time;
     202
     203	import java.util.Date;
     204
     205	public class UnixTime {
     206	    private final int value;
     207
     208	    public UnixTime(int value) {
     209	        this.value = value;
     210	    }
     211
     212	    public int getValue() {
     213	        return value;
     214	    }
     215
     216	    @Override
     217	    public String toString() {
     218	        return new Date(value * 1000L).toString();
     219	    }
     220	}

     现在让我们重新修改TimeDecoder实现，让其返回一个UnixTime，而不是一个ChannelBuffer。
     Java代码 )
     221	@Override
     222	protected Object decode(
     223	        ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) {
     224	    if (buffer.readableBytes() < 4) {
     225	        return null;
     226	    }
     227
     228	    return new UnixTime(buffer.readInt());
     229	}

     FrameDecoder和ReplayingDecoder允许你返回一个任何类型的对象。如果它们仅允许返回一个ChannelBuffer类型的对象，我们将不得不插入另一个可以从ChannelBuffer对象转换 为UnixTime对象的ChannelHandler实现。


     有了这个修改后的decoder实现，这个TimeClientHandler便不会再依赖ChannelBuffer。
     Java代码 *
     230	@Override
     231	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
     232	    UnixTime m = (UnixTime) e.getMessage();
     233	    System.out.println(m);
     234	    e.getChannel().close();
     235	}

     更加简单优雅了，不是吗？同样的技巧也可以应用在服务端，让我们现在更新TimeServerHandler的实现：
     Java代码 +
     236	@Override
     237	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
     238	    UnixTime time = new UnixTime(System.currentTimeMillis() / 1000);
     239	    ChannelFuture f = e.getChannel().write(time);
     240	    f.addListener(ChannelFutureListener.CLOSE);
     241	}

     现在剩下的唯一需要修改的部分是这个ChannelHandler实现，这个ChannelHandler实现需要把一个UnixTime对象重新转换为一个ChannelBuffer。但这却已是相当简单了，因为当你对消息进行编码的时候你不再需要处理数据包的拆分及组装。
     Java代码 -
     242	package org.jboss.netty.example.time;
     243
     244	import static org.jboss.netty.buffer.ChannelBuffers.*;
     245
     246	@ChannelPipelineCoverage("all")
     247	public class TimeEncoder extends SimpleChannelHandler {
     248
     249	    public void writeRequested(ChannelHandlerContext ctx, MessageEvent  e) {
     250	        UnixTime time = (UnixTime) e.getMessage();
     251
     252	        ChannelBuffer buf = buffer(4);
     253	        buf.writeInt(time.getValue());
     254
     255	        Channels.write(ctx, e.getFuture(), buf);
     256	    }
     257	}

     代码说明
     1) 因为这个encoder是无状态的，所以其使用的ChannelPipelineCoverage注解值是“all”。实际上，大多数encoder实现都是无状态的。

     2) 一个encoder通过重写writeRequested方法来实现对写操作请求的拦截。不过请注意虽然这个writeRequested方法使用了和 messageReceived方法一样的MessageEvent参数，但是它们却分别对应了不同的解释。一个ChannelEvent事件可以既是一个上升流事件（upstream event）也可以是一个下降流事件（downstream event），这取决于事件流的方向。例如：一个MessageEvent消息事件可以作为一个上升流事件（upstream event）被messageReceived方法调用，也可以作为一个下降流事件（downstream event）被writeRequested方法调用。请参考API手册获得上升流事件（upstream event）和下降流事件（downstream event）的更多信息。

     3) 一旦完成了POJO和ChannelBuffer转换，你应当确保把这个新的buffer缓冲转发至先前的 ChannelDownstreamHandler处理，这个下降通道的处理器由某个ChannelPipeline管理。Channels提供了多个可以创建和发送ChannelEvent事件的帮助方法。在这个例子中，Channels.write(...)方法创建了一个新的 MessageEvent事件，并把这个事件发送给了先前的处于某个ChannelPipeline内的 ChannelDownstreamHandler处理器。

     另外，一个很不错的方法是使用静态的方式导入Channels类：

     import static org.jboss.netty.channel.Channels.*;
     ...
     ChannelPipeline pipeline = pipeline();
     write(ctx, e.getFuture(), buf);
     fireChannelDisconnected(ctx);



     最后的任务是把这个TimeEncoder插入服务端的ChannelPipeline，这是一个很简单的步骤。

     1.9. 关闭你的应用

     如果你运行了TimeClient，你肯定可以注意到，这个应用并没有自动退出而只是在那里保持着无意义的运行。跟踪堆栈记录你可以发现，这里有一些运行状态的I/O线程。为了关闭这些I/O线程并让应用优雅的退出，你需要释放这些由ChannelFactory分配的资源。

     一个典型的网络应用的关闭过程由以下三步组成：
     ·	关闭负责接收所有请求的server socket。
     ·	关闭所有客户端socket或服务端为响应某个请求而创建的socket。
     ·	释放ChannelFactory使用的所有资源。
     为了让TimeClient执行这三步，你需要在TimeClient.main()方法内关闭唯一的客户连接以及ChannelFactory使用的所有资源，这样做便可以优雅的关闭这个应用。
     Java代码 0
     258	package org.jboss.netty.example.time;
     259
     260	public class TimeClient {
     261	    public static void main(String[] args) throws Exception {
     262	        ...
     263	        ChannelFactory factory = ...;
     264	        ClientBootstrap bootstrap = ...;
     265	        ...
     266	        ChannelFuture future  = bootstrap.connect(...);
     267	        future.awaitUninterruptible();
     268	        if (!future.isSuccess()) {
     269	            future.getCause().printStackTrace();
     270	        }
     271	        future.getChannel().getCloseFuture().awaitUninterruptibly();
     272	        factory.releaseExternalResources();
     273	    }
     274	}
     代码说明
     1) ClientBootstrap对象的connect方法返回一个ChannelFuture对象，这个ChannelFuture对象将告知这个连接操作的成功或失败状态。同时这个ChannelFuture对象也保存了一个代表这个连接操作的Channel对象引用。

     2) 阻塞式的等待，直到ChannelFuture对象返回这个连接操作的成功或失败状态。

     3) 如果连接失败，我们将打印连接失败的原因。如果连接操作没有成功或者被取消，ChannelFuture对象的getCause()方法将返回连接失败的原因。

     4) 现在，连接操作结束，我们需要等待并且一直到这个Channel通道返回的closeFuture关闭这个连接。每一个Channel都可获得自己的closeFuture对象，因此我们可以收到通知并在这个关闭时间点执行某种操作。

     并且即使这个连接操作失败，这个closeFuture仍旧会收到通知，因为这个代表连接的 Channel对象将会在连接操作失败后自动关闭。

     5) 在这个时间点，所有的连接已被关闭。剩下的唯一工作是释放ChannelFactory通道工厂使用的资源。这一步仅需要调用 releaseExternalResources()方法即可。包括NIO Secector和线程池在内的所有资源将被自动的关闭和终止。


     关闭一个客户端应用是很简单的，但又该如何关闭一个服务端应用呢？你需要释放其绑定的端口并关闭所有接受和打开的连接。为了做到这一点，你需要使用一种数据结构记录所有的活动连接，但这却并不是一件容易的事。幸运的是，这里有一种解决方案，ChannelGroup。

     ChannelGroup是Java 集合 API的一个特有扩展，ChannelGroup内部持有所有打开状态的Channel通道。如果一个Channel通道对象被加入到ChannelGroup，如果这个Channel通道被关闭，ChannelGroup将自动移除这个关闭的Channel通道对象。此外，你还可以对一个ChannelGroup对象内部的所有Channel通道对象执行相同的操作。例如，当你关闭服务端应用时你可以关闭一个ChannelGroup内部的所有Channel通道对象。

     为了记录所有打开的socket，你需要修改你的TimeServerHandler实现，将一个打开的Channel通道加入全局的ChannelGroup对象，TimeServer.allChannels:
     Java代码 2
     275	@Override
     276	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
     277	    TimeServer.allChannels.add(e.getChannel());
     278	}
     代码说明
     是的，ChannelGroup是线程安全的。

     现在，所有活动的Channel通道将被自动的维护，关闭一个服务端应用有如关闭一个客户端应用一样简单。
     Java代码 3
     279	package org.jboss.netty.example.time;
     280
     281	public class TimeServer {
     282
     283	    static final ChannelGroup allChannels = new DefaultChannelGroup("time-server" );
     284
     285	    public static void main(String[] args) throws Exception {
     286	        ...
     287	        ChannelFactory factory = ...;
     288	        ServerBootstrap bootstrap = ...;
     289	        ...
     290	        Channel channel  = bootstrap.bind(...);
     291	        allChannels.add(channel);
     292	        waitForShutdownCommand();
     293	        ChannelGroupFuture future = allChannels.close();
     294	        future.awaitUninterruptibly();
     295	        factory.releaseExternalResources();
     296	    }
     297	}
     代码说明
     1) DefaultChannelGroup需要一个组名作为其构造器参数。这个组名仅是区分每个ChannelGroup的一个标示。

     2) ServerBootstrap对象的bind方法返回了一个绑定了本地地址的服务端Channel通道对象。调用这个Channel通道的close()方法将释放这个Channel通道绑定的本地地址。

     3) 不管这个Channel对象属于服务端，客户端，还是为响应某一个请求创建，任何一种类型的Channel对象都会被加入ChannelGroup。因此，你尽可在关闭服务时关闭所有的Channel对象。

     4) waitForShutdownCommand()是一个想象中等待关闭信号的方法。你可以在这里等待某个客户端的关闭信号或者JVM的关闭回调命令。

     5) 你可以对ChannelGroup管理的所有Channel对象执行相同的操作。在这个例子里，我们将关闭所有的通道，这意味着绑定在服务端特定地址的 Channel通道将解除绑定，所有已建立的连接也将异步关闭。为了获得成功关闭所有连接的通知，close()方法将返回一个 ChannelGroupFuture对象，这是一个类似ChannelFuture的对象。


     1.10. 总述

     在这一章节，我们快速浏览并示范了如何使用Netty开发网络应用。下一章节将涉及更多的问题。同时请记住，为了帮助你以及能够让Netty基于你的回馈得到持续的改进和提高，Netty社区 将永远欢迎你的问题及建议。
     第二章. 架构总览

     6

     在这个章节，我们将阐述Netty提供的核心功能以及在此基础之上如何构建一个完备的网络应用。

     2.1. 丰富的缓冲实现

     Netty使用自建的buffer API，而不是使用NIO的ByteBuffer来代表一个连续的字节序列。与ByteBuffer相比这种方式拥有明显的优势。Netty使用新的buffer类型ChannelBuffer，ChannelBuffer被设计为一个可从底层解决ByteBuffer问题，并可满足日常网络应用开发需要的缓冲类型。这些很酷的特性包括：

     ·	如果需要，允许使用自定义的缓冲类型。
     ·	复合缓冲类型中内置的透明的零拷贝实现。
     ·	开箱即用的动态缓冲类型，具有像StringBuffer一样的动态缓冲能力。
     ·	不再需要调用的flip()方法。
     ·	正常情况下具有比ByteBuffer更快的响应速度。
     更多信息请参考：org.jboss.netty.buffer package description

     2.2. 统一的异步 I/O API

     传统的Java I/O API在应对不同的传输协议时需要使用不同的类型和方法。例如：java.net.Socket 和 java.net.DatagramSocket它们并不具有相同的超类型，因此，这就需要使用不同的调用方式执行socket操作。

     这种模式上的不匹配使得在更换一个网络应用的传输协议时变得繁杂和困难。由于（Java I/O API）缺乏协议间的移植性，当你试图在不修改网络传输层的前提下增加多种协议的支持，这时便会产生问题。并且理论上讲，多种应用层协议可运行在多种传输层协议之上例如TCP/IP,UDP/IP,SCTP和串口通信。

     让这种情况变得更糟的是，Java新的I/O（NIO）API与原有的阻塞式的I/O（OIO）API并不兼容，NIO.2(AIO)也是如此。由于所有的API无论是在其设计上还是性能上的特性都与彼此不同，在进入开发阶段，你常常会被迫的选择一种你需要的API。

     例如，在用户数较小的时候你可能会选择使用传统的OIO(Old I/O) API，毕竟与NIO相比使用OIO将更加容易一些。然而，当你的业务呈指数增长并且服务器需要同时处理成千上万的客户连接时你便会遇到问题。这种情况下你可能会尝试使用NIO，但是复杂的NIO Selector编程接口又会耗费你大量时间并最终会阻碍你的快速开发。

     Netty有一个叫做Channel的统一的异步I/O编程接口，这个编程接口抽象了所有点对点的通信操作。也就是说，如果你的应用是基于Netty的某一种传输实现，那么同样的，你的应用也可以运行在Netty的另一种传输实现上。Netty提供了几种拥有相同编程接口的基本传输实现：

     ·	NIO-based TCP/IP transport (See org.jboss.netty.channel.socket.nio),
     ·	OIO-based TCP/IP transport (See org.jboss.netty.channel.socket.oio),
     ·	OIO-based UDP/IP transport, and
     ·	Local transport (See org.jboss.netty.channel.local).
     切换不同的传输实现通常只需对代码进行几行的修改调整，例如选择一个不同的ChannelFactory实现。

     此外，你甚至可以利用新的传输实现没有写入的优势，只需替换一些构造器的调用方法即可，例如串口通信。而且由于核心API具有高度的可扩展性，你还可以完成自己的传输实现。

     2.3. 基于拦截链模式的事件模型

     一个定义良好并具有扩展能力的事件模型是事件驱动开发的必要条件。Netty具有定义良好的I/O事件模型。由于严格的层次结构区分了不同的事件类型，因此Netty也允许你在不破坏现有代码的情况下实现自己的事件类型。这是与其他框架相比另一个不同的地方。很多NIO框架没有或者仅有有限的事件模型概念；在你试图添加一个新的事件类型的时候常常需要修改已有的代码，或者根本就不允许你进行这种扩展。

     在一个ChannelPipeline内部一个ChannelEvent被一组ChannelHandler处理。这个管道是拦截过滤器 模式的一种高级形式的实现，因此对于一个事件如何被处理以及管道内部处理器间的交互过程，你都将拥有绝对的控制力。例如，你可以定义一个从socket读取到数据后的操作：
     Java代码 8
     1	public class MyReadHandler implements SimpleChannelHandler {
     2	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent evt) {
     3	        Object message = evt.getMessage();
     4	        // Do something with the received message.
     5	        ...
     6
     7	        // And forward the event to the next handler.
     8	        ctx.sendUpstream(evt);
     9	    }
     10	}

     同时你也可以定义一种操作响应其他处理器的写操作请求：
     Java代码 9
     11	public class MyWriteHandler implements SimpleChannelHandler {
     12	    public void writeRequested(ChannelHandlerContext ctx, MessageEvent evt) {
     13	        Object message = evt.getMessage();
     14	        // Do something with the message to be written.
     15	        ...
     16
     17	        // And forward the event to the next handler.
     18	        ctx.sendDownstream(evt);
     19	    }
     20	}

     有关事件模型的更多信息，请参考API文档ChannelEvent和ChannelPipeline部分。

     2.4. 适用快速开发的高级组件

     上述所提及的核心组件已经足够实现各种类型的网络应用，除此之外，Netty也提供了一系列的高级组件来加速你的开发过程。

     2.4.1. Codec框架

     就像“1.8. 使用POJO代替ChannelBuffer”一节所展示的那样，从业务逻辑代码中分离协议处理部分总是一个很不错的想法。然而如果一切从零开始便会遭遇到实现上的复杂性。你不得不处理分段的消息。一些协议是多层的（例如构建在其他低层协议之上的协议）。一些协议过于复杂以致难以在一台主机（single state machine）上实现。

     因此，一个好的网络应用框架应该提供一种可扩展，可重用，可单元测试并且是多层的codec框架，为用户提供易维护的codec代码。

     Netty提供了一组构建在其核心模块之上的codec实现，这些简单的或者高级的codec实现帮你解决了大部分在你进行协议处理开发过程会遇到的问题，无论这些协议是简单的还是复杂的，二进制的或是简单文本的。

     2.4.2. SSL / TLS 支持

     不同于传统阻塞式的I/O实现，在NIO模式下支持SSL功能是一个艰难的工作。你不能只是简单的包装一下流数据并进行加密或解密工作，你不得不借助于javax.net.ssl.SSLEngine，SSLEngine是一个有状态的实现，其复杂性不亚于SSL自身。你必须管理所有可能的状态，例如密码套件，密钥协商（或重新协商），证书交换以及认证等。此外，与通常期望情况相反的是SSLEngine甚至不是一个绝对的线程安全实现。

     在Netty内部，SslHandler封装了所有艰难的细节以及使用SSLEngine可能带来的陷阱。你所做的仅是配置并将该SslHandler插入到你的ChannelPipeline中。同样Netty也允许你实现像StartTlS 那样所拥有的高级特性，这很容易。

     2.4.3. HTTP实现

     HTTP无疑是互联网上最受欢迎的协议，并且已经有了一些例如Servlet容器这样的HTTP实现。因此，为什么Netty还要在其核心模块之上构建一套HTTP实现？

     与现有的HTTP实现相比Netty的HTTP实现是相当与众不同的。在HTTP消息的低层交互过程中你将拥有绝对的控制力。这是因为Netty的HTTP实现只是一些HTTP codec和HTTP消息类的简单组合，这里不存在任何限制——例如那种被迫选择的线程模型。你可以随心所欲的编写那种可以完全按照你期望的工作方式工作的客户端或服务器端代码。这包括线程模型，连接生命期，快编码，以及所有HTTP协议允许你做的，所有的一切，你都将拥有绝对的控制力。

     由于这种高度可定制化的特性，你可以开发一个非常高效的HTTP服务器，例如：
     ·	要求持久化链接以及服务器端推送技术的聊天服务（e.g. Comet ）
     ·	需要保持链接直至整个文件下载完成的媒体流服务（e.g. 2小时长的电影）
     ·	需要上传大文件并且没有内存压力的文件服务（e.g. 上传1GB文件的请求）
     ·	支持大规模mash-up应用以及数以万计连接的第三方web services异步处理平台
     2.4.4. Google Protocol Buffer 整合

     Google Protocol Buffers 是快速实现一个高效的二进制协议的理想方案。通过使用ProtobufEncoder和ProtobufDecoder，你可以把Google Protocol Buffers 编译器 (protoc)生成的消息类放入到Netty的codec实现中。请参考“LocalTime ”实例，这个例子也同时显示出开发一个由简单协议定义 的客户及服务端是多么的容易。

     2.5. 总述
     在这一章节，我们从功能特性的角度回顾了Netty的整体架构。Netty有一个简单却不失强大的架构。这个架构由三部分组成——缓冲（buffer），通道（channel），事件模型（event model）——所有的高级特性都构建在这三个核心组件之上。一旦你理解了它们之间的工作原理，你便不难理解在本章简要提及的更多高级特性。

     你可能对Netty的整体架构以及每一部分的工作原理仍旧存有疑问。如果是这样，最好的方式是告诉我们 应该如何改进这份指南。

     */
}
