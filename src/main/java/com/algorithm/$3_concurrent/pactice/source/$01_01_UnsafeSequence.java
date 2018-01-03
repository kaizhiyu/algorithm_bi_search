package com.algorithm.$3_concurrent.pactice.source;

import com.algorithm.$8_annotation.*;

/**
 * $01_01_UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */
//https://baike.baidu.com/item/%E7%AB%9E%E6%80%81%E6%9D%A1%E4%BB%B6/1321097?fr=aladdin
@Doc4Desc("竞态条件（race condition）是指设备或系统出现不恰当的执行时序，而得到不正确的结果。")
@Doc4Desc("竞态条件（race condition），从多进程间通信的角度来讲，是指两个或多个进程对共享的数据进行读或写的操作时，最终的结果取决于这些进程的执行顺序。")
@NotThreadSafe
public class $01_01_UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}
