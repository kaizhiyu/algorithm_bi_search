package com.algorithm.$3_concurrent.jvm;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @auth v_fanhaibo on   2018/1/18
 */
@SearchKeyWord("TLAB thread local allocation buffer")
public class JVM_deep_in {
//    http://blog.csdn.net/hxpjava1/article/details/58591947

    /**
     * 2. TLAB
         JVM在内存新生代Eden Space中开辟了一小块线程私有的区域，称作TLAB（Thread-local allocation buffer）。
         默认设定为占用Eden Space的1%。在Java程序中很多对象都是小对象且用过即丢，它们不存在线程共享也适合被快速GC，
         所以对于小对象通常JVM会优先分配在TLAB上，并且TLAB上的分配由于是线程私有所以没有锁开销。
         因此在实践中分配多个小对象的效率通常比分配一个大对象的效率要高。
         也就是说，Java中每个线程都会有自己的缓冲区称作TLAB（Thread-local allocation buffer），
         每个TLAB都只有一个线程可以操作，TLAB结合bump-the-pointer技术可以实现快速的对象分配，
         而不需要任何的锁进行同步，也就是说，在对象分配的时候不用锁住整个堆，而只需要在自己的缓冲区分配即可。
         关于对象分配的JDK源码可以参见JVM 之 Java对象创建[初始化]中对OpenJDK源码的分析。

     3. Java对象分配的过程
         编译器通过逃逸分析，确定对象是在栈上分配还是在堆上分配。如果是在堆上分配，则进入选项2.

         如果tlab_top + size <= tlab_end，则在在TLAB上直接分配对象并增加tlab_top 的值，如果现有的TLAB不足以存放当前对象则3.

         重新申请一个TLAB，并再次尝试存放当前对象。如果放不下，则4.

         在Eden区加锁（这个区是多线程共享的），如果eden_top + size <= eden_end则将对象存放在Eden区，增加eden_top 的值，如果Eden区不足以存放，则5.

         执行一次Young GC（minor collection）。

         经过Young GC之后，如果Eden区任然不足以存放当前对象，则直接分配到老年代。

         对象不在堆上分配主要的原因还是堆是共享的，在堆上分配有锁的开销。
         无论是TLAB还是栈都是线程私有的，私有即避免了竞争（当然也可能产生额外的问题例如可见性问题），这是典型的用空间换效率的做法。
     */
}
