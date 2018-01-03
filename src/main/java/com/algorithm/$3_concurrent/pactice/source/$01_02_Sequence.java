package com.algorithm.$3_concurrent.pactice.source;

import com.algorithm.$8_annotation.*;

/**
 * $01_02_Sequence
 *
 * @author Brian Goetz and Tim Peierls
 * //https://baike.baidu.com/item/%E7%BA%BF%E7%A8%8B%E8%B0%83%E5%BA%A6%E5%99%A8/2154102?fr=aladdin
 * 线程调度器: 操作系统的核心，它实际就是一个常驻内存的程序，不断地对线程队列进行扫描，
 * 利用特定的算法（时间片轮转法、优先级调度法、多级反馈队列调度法等）找出比当前占有CPU的线程更有CPU使用权的线程，
 * 并从之前的线程中收回处理器，再使待运行的线程占用处理器。
 */

@Doc4Desc("监视器模式")
@ThreadSafe
public class $01_02_Sequence {

    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
