package com.algorithm.$18_io.nio;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @auth v_fanhaibo on   2018/1/17
 */

@SearchKeyWord("NIO selector 原理")
public class NIOSelector_Theory_2 {

/**
 *
 * 深入浅出NIO之Selector实现原理
 96  占小狼  595a1b60 08f6 4beb 998f 2bf55e230555 关注
 2016.07.29 11:47* 字数 2331 阅读 20490评论 18喜欢 110赞赏 4
 简书 占小狼 转载请注明原创出处，谢谢！
 前言

 Java NIO 由以下几个核心部分组成：
 1、Buffer
 2、Channel
 3、Selector

 Buffer和Channel在深入浅出NIO之Channel、Buffer一文中已经介绍过，本文主要讲解NIO的Selector实现原理。

 之前进行socket编程时，accept方法会一直阻塞，直到有客户端请求的到来，并返回socket进行相应的处理。整个过程是流水线的，处理完一个请求，才能去获取并处理后面的请求，当然也可以把获取socket和处理socket的过程分开，一个线程负责accept，一个线程池负责处理请求。

 但NIO提供了更好的解决方案，采用选择器（Selector）返回已经准备好的socket，并按顺序处理，基于通道（Channel）和缓冲区（Buffer）来进行数据的传输。

 Selector

 这里出来一个新概念，selector，具体是一个什么样的东西？

 想想一个场景：在一个养鸡场，有这么一个人，每天的工作就是不停检查几个特殊的鸡笼，如果有鸡进来，有鸡出去，有鸡生蛋，有鸡生病等等，就把相应的情况记录下来，如果鸡场的负责人想知道情况，只需要询问那个人即可。

 在这里，这个人就相当Selector，每个鸡笼相当于一个SocketChannel，每个线程通过一个Selector可以管理多个SocketChannel。


 为了实现Selector管理多个SocketChannel，必须将具体的SocketChannel对象注册到Selector，并声明需要监听的事件（这样Selector才知道需要记录什么数据），一共有4种事件：

 1、connect：客户端连接服务端事件，对应值为SelectionKey.OP_CONNECT(8)
 2、accept：服务端接收客户端连接事件，对应值为SelectionKey.OP_ACCEPT(16)
 3、read：读事件，对应值为SelectionKey.OP_READ(1)
 4、write：写事件，对应值为SelectionKey.OP_WRITE(4)

 这个很好理解，每次请求到达服务器，都是从connect开始，connect成功后，服务端开始准备accept，准备就绪，开始读数据，并处理，最后写回数据返回。

 所以，当SocketChannel有对应的事件发生时，Selector都可以观察到，并进行相应的处理。

 服务端代码

 为了更好的理解，先看一段服务端的示例代码

 ServerSocketChannel serverChannel = ServerSocketChannel.open();
 serverChannel.configureBlocking(false);
 serverChannel.socket().bind(new InetSocketAddress(port));
 Selector selector = Selector.open();
 serverChannel.register(selector, SelectionKey.OP_ACCEPT);
 while(true){
 int n = selector.select();
 if (n == 0) continue;
 Iterator ite = this.selector.selectedKeys().iterator();
 while(ite.hasNext()){
 SelectionKey key = (SelectionKey)ite.next();
 if (key.isAcceptable()){
 SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
 clntChan.configureBlocking(false);
 //将选择器注册到连接到的客户端信道，
 //并指定该信道key值的属性为OP_READ，
 //同时为该信道指定关联的附件
 clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
 }
 if (key.isReadable()){
 handleRead(key);
 }
 if (key.isWritable() && key.isValid()){
 handleWrite(key);
 }
 if (key.isConnectable()){
 System.out.println("isConnectable = true");
 }
 ite.remove();
 }
 }
 服务端操作过程

 1、创建ServerSocketChannel实例，并绑定指定端口；
 2、创建Selector实例；
 3、将serverSocketChannel注册到selector，并指定事件OP_ACCEPT，最底层的socket通过channel和selector建立关联；
 4、如果没有准备好的socket，select方法会被阻塞一段时间并返回0；
 5、如果底层有socket已经准备好，selector的select方法会返回socket的个数，而且selectedKeys方法会返回socket对应的事件（connect、accept、read or write）；
 6、根据事件类型，进行不同的处理逻辑；

 在步骤3中，selector只注册了serverSocketChannel的OP_ACCEPT事件
 1、如果有客户端A连接服务，执行select方法时，可以通过serverSocketChannel获取客户端A的socketChannel，并在selector上注册socketChannel的OP_READ事件。
 2、如果客户端A发送数据，会触发read事件，这样下次轮询调用select方法时，就能通过socketChannel读取数据，同时在selector上注册该socketChannel的OP_WRITE事件，实现服务器往客户端写数据。

 Selector实现原理

 SocketChannel、ServerSocketChannel和Selector的实例初始化都通过SelectorProvider类实现，其中Selector是整个NIO Socket的核心实现。

 public static SelectorProvider provider() {
 synchronized (lock) {
 if (provider != null)
 return provider;
 return AccessController.doPrivileged(
 new PrivilegedAction<SelectorProvider>() {
 public SelectorProvider run() {
 if (loadProviderFromProperty())
 return provider;
 if (loadProviderAsService())
 return provider;
 provider = sun.nio.ch.DefaultSelectorProvider.create();
 return provider;
 }
 });
 }
 }
 SelectorProvider在windows和linux下有不同的实现，provider方法会返回对应的实现。

 这里不禁要问，Selector是如何做到同时管理多个socket？

 下面我们看看Selector的具体实现，Selector初始化时，会实例化PollWrapper、SelectionKeyImpl数组和Pipe。

 WindowsSelectorImpl(SelectorProvider sp) throws IOException {
 super(sp);
 pollWrapper = new PollArrayWrapper(INIT_CAP);
 wakeupPipe = Pipe.open();
 wakeupSourceFd = ((SelChImpl)wakeupPipe.source()).getFDVal();

 // Disable the Nagle algorithm so that the wakeup is more immediate
 SinkChannelImpl sink = (SinkChannelImpl)wakeupPipe.sink();
 (sink.sc).socket().setTcpNoDelay(true);
 wakeupSinkFd = ((SelChImpl)sink).getFDVal();
 pollWrapper.addWakeupSocket(wakeupSourceFd, 0);
 }
 pollWrapper用Unsafe类申请一块物理内存pollfd，存放socket句柄fdVal和events，其中pollfd共8位，0-3位保存socket句柄，4-7位保存events。



 pollWrapper提供了fdVal和event数据的相应操作，如添加操作通过Unsafe的putInt和putShort实现。

 void putDescriptor(int i, int fd) {
 pollArray.putInt(SIZE_POLLFD * i + FD_OFFSET, fd);
 }
 void putEventOps(int i, int event) {
 pollArray.putShort(SIZE_POLLFD * i + EVENT_OFFSET, (short)event);
 }
 先看看serverChannel.register(selector, SelectionKey.OP_ACCEPT)是如何实现的

 public final SelectionKey register(Selector sel, int ops, Object att)
 throws ClosedChannelException {
 synchronized (regLock) {
 SelectionKey k = findKey(sel);
 if (k != null) {
 k.interestOps(ops);
 k.attach(att);
 }
 if (k == null) {
 // New registration
 synchronized (keyLock) {
 if (!isOpen())
 throw new ClosedChannelException();
 k = ((AbstractSelector)sel).register(this, ops, att);
 addKey(k);
 }
 }
 return k;
 }
 }
 如果该channel和selector已经注册过，则直接添加事件和附件。
 否则通过selector实现注册过程。
 protected final SelectionKey register(AbstractSelectableChannel ch,
 int ops,  Object attachment) {
 if (!(ch instanceof SelChImpl))
 throw new IllegalSelectorException();
 SelectionKeyImpl k = new SelectionKeyImpl((SelChImpl)ch, this);
 k.attach(attachment);
 synchronized (publicKeys) {
 implRegister(k);
 }
 k.interestOps(ops);
 return k;
 }

 protected void implRegister(SelectionKeyImpl ski) {
 synchronized (closeLock) {
 if (pollWrapper == null)
 throw new ClosedSelectorException();
 growIfNeeded();
 channelArray[totalChannels] = ski;
 ski.setIndex(totalChannels);
 fdMap.put(ski);
 keys.add(ski);
 pollWrapper.addEntry(totalChannels, ski);
 totalChannels++;
 }
 }
 1、以当前channel和selector为参数，初始化SelectionKeyImpl 对象selectionKeyImpl ，并添加附件attachment。
 2、如果当前channel的数量totalChannels等于SelectionKeyImpl数组大小，对SelectionKeyImpl数组和pollWrapper进行扩容操作。
 3、如果totalChannels % MAX_SELECTABLE_FDS == 0，则多开一个线程处理selector。
 4、pollWrapper.addEntry将把selectionKeyImpl中的socket句柄添加到对应的pollfd。
 5、k.interestOps(ops)方法最终也会把event添加到对应的pollfd。

 所以，不管serverSocketChannel，还是socketChannel，在selector注册的事件，最终都保存在pollArray中。

 接着，再来看看selector中的select是如何实现一次获取多个有事件发生的channel的，底层由selector实现类的doSelect方法实现，如下：

 protected int doSelect(long timeout) throws IOException {
 if (channelArray == null)
 throw new ClosedSelectorException();
 this.timeout = timeout; // set selector timeout
 processDeregisterQueue();
 if (interruptTriggered) {
 resetWakeupSocket();
 return 0;
 }
 // Calculate number of helper threads needed for poll. If necessary
 // threads are created here and start waiting on startLock
 adjustThreadsCount();
 finishLock.reset(); // reset finishLock
 // Wakeup helper threads, waiting on startLock, so they start polling.
 // Redundant threads will exit here after wakeup.
 startLock.startThreads();
 // do polling in the main thread. Main thread is responsible for
 // first MAX_SELECTABLE_FDS entries in pollArray.
 try {
 begin();
 try {
 subSelector.poll();
 } catch (IOException e) {
 finishLock.setException(e); // Save this exception
 }
 // Main thread is out of poll(). Wakeup others and wait for them
 if (threads.size() > 0)
 finishLock.waitForHelperThreads();
 } finally {
 end();
 }
 // Done with poll(). Set wakeupSocket to nonsignaled  for the next run.
 finishLock.checkForException();
 processDeregisterQueue();
 int updated = updateSelectedKeys();
 // Done with poll(). Set wakeupSocket to nonsignaled  for the next run.
 resetWakeupSocket();
 return updated;
 }
 其中 subSelector.poll() 是select的核心，由native函数poll0实现，readFds、writeFds 和exceptFds数组用来保存底层select的结果，数组的第一个位置都是存放发生事件的socket的总数，其余位置存放发生事件的socket句柄fd。

 private final int[] readFds = new int [MAX_SELECTABLE_FDS + 1];
 private final int[] writeFds = new int [MAX_SELECTABLE_FDS + 1];
 private final int[] exceptFds = new int [MAX_SELECTABLE_FDS + 1];
 private int poll() throws IOException{ // poll for the main thread
 return poll0(pollWrapper.pollArrayAddress,
 Math.min(totalChannels, MAX_SELECTABLE_FDS),
 readFds, writeFds, exceptFds, timeout);
 }
 执行 selector.select() ，poll0函数把指向socket句柄和事件的内存地址传给底层函数。
 1、如果之前没有发生事件，程序就阻塞在select处，当然不会一直阻塞，因为epoll在timeout时间内如果没有事件，也会返回；
 2、一旦有对应的事件发生，poll0方法就会返回；
 3、processDeregisterQueue方法会清理那些已经cancelled的SelectionKey；
 4、updateSelectedKeys方法统计有事件发生的SelectionKey数量，并把符合条件发生事件的SelectionKey添加到selectedKeys哈希表中，提供给后续使用。

 在早期的JDK1.4和1.5 update10版本之前，Selector基于select/poll模型实现，是基于IO复用技术的非阻塞IO，不是异步IO。在JDK1.5 update10和linux core2.6以上版本，sun优化了Selctor的实现，底层使用epoll替换了select/poll。

 read实现

 通过遍历selector中的SelectionKeyImpl数组，获取发生事件的socketChannel对象，其中保存了对应的socket，实现如下

 public int read(ByteBuffer buf) throws IOException {
 if (buf == null)
 throw new NullPointerException();
 synchronized (readLock) {
 if (!ensureReadOpen())
 return -1;
 int n = 0;
 try {
 begin();
 synchronized (stateLock) {
 if (!isOpen()) {
 return 0;
 }
 readerThread = NativeThread.current();
 }
 for (;;) {
 n = IOUtil.read(fd, buf, -1, nd);
 if ((n == IOStatus.INTERRUPTED) && isOpen()) {
 // The system call was interrupted but the channel
 // is still open, so retry
 continue;
 }
 return IOStatus.normalize(n);
 }
 } finally {
 readerCleanup();        // Clear reader thread
 // The end method, which
 end(n > 0 || (n == IOStatus.UNAVAILABLE));

 // Extra case for socket channels: Asynchronous shutdown
 //
 synchronized (stateLock) {
 if ((n <= 0) && (!isInputOpen))
 return IOStatus.EOF;
 }
 assert IOStatus.check(n);
 }
 }
 }
 最终通过Buffer的方式读取socket的数据。

 wakeup实现

 public Selector wakeup() {
 synchronized (interruptLock) {
 if (!interruptTriggered) {
 setWakeupSocket();
 interruptTriggered = true;
 }
 }
 return this;
 }

 // Sets Windows wakeup socket to a signaled state.
 private void setWakeupSocket() {
 setWakeupSocket0(wakeupSinkFd);
 }
 private native void setWakeupSocket0(int wakeupSinkFd);
 看来wakeupSinkFd这个变量是为wakeup方法使用的。
 其中interruptTriggered为中断已触发标志，当pollWrapper.interrupt()之后，该标志即为true了；因为这个标志，连续两次wakeup，只会有一次效果。

 epoll原理

 epoll是Linux下的一种IO多路复用技术，可以非常高效的处理数以百万计的socket句柄。

 三个epoll相关的系统调用：

 int epoll_create(int size)
 epoll_create建立一个epoll对象。参数size是内核保证能够正确处理的最大句柄数，多于这个最大数时内核可不保证效果。
 int epoll_ctl(int epfd, int op, int fd, struct epoll_event event)
 epoll_ctl可以操作epoll_create创建的epoll，如将socket句柄加入到epoll中让其监控，或把epoll正在监控的某个socket句柄移出epoll。
 int epoll_wait(int epfd, struct epoll_event events,int maxevents, int timeout)
 epoll_wait在调用时，在给定的timeout时间内，所监控的句柄中有事件发生时，就返回用户态的进程。
 epoll内部实现大概如下：

 epoll初始化时，会向内核注册一个文件系统，用于存储被监控的句柄文件，调用epoll_create时，会在这个文件系统中创建一个file节点。同时epoll会开辟自己的内核高速缓存区，以红黑树的结构保存句柄，以支持快速的查找、插入、删除。还会再建立一个list链表，用于存储准备就绪的事件。
 当执行epoll_ctl时，除了把socket句柄放到epoll文件系统里file对象对应的红黑树上之外，还会给内核中断处理程序注册一个回调函数，告诉内核，如果这个句柄的中断到了，就把它放到准备就绪list链表里。所以，当一个socket上有数据到了，内核在把网卡上的数据copy到内核中后，就把socket插入到就绪链表里。
 当epoll_wait调用时，仅仅观察就绪链表里有没有数据，如果有数据就返回，否则就sleep，超时时立刻返回。
 *
 *
 *
 *
 */
     

}