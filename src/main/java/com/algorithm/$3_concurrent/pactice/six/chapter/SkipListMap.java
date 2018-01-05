package com.algorithm.$3_concurrent.pactice.six.chapter;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/5.
 * @version:v1.0
 */

public class SkipListMap {


    /**
     *  //http://blog.csdn.net/bigtree_3721/article/details/51291974
     *  
     * Ø1:  ConcurrentSkipListMap具有Skip list的性质 ，并且适用于大规模数据的并发访问。
     *      多个线程可以安全地并发执行插入、移除、更新和访问操作。与其他有锁机制的数据结构在巨大的压力下相比有优势。
     * Ø2:  TreeMap插入数据时平衡树采用严格的旋转（比如平衡二叉树有左旋右旋）来保证平衡，
     *      因此Skip list比较容易实现，而且相比平衡树有着较高的运行效率。
     *
     *
     */
}
