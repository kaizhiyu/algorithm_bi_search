package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$8_annotation.Doc4Desc;
import com.algorithm.$8_annotation.ThreadSafe;

/**
 * @author fanhb on 2018/1/1
 * @desc xxxx
 */
@Doc4Desc("模拟cas,只表示语义,不表示性能")
@ThreadSafe
public class SimulatedCAS {
    private int value;

    public synchronized int get(){
        return this.value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newVal){

        int oldValue = this.value;
        if (expectedValue == oldValue){
            this.value = newVal;
        }//
        return oldValue;
    }


    public synchronized boolean compareAndSet(int expectedValue, int newVal){

        return (expectedValue == compareAndSwap(expectedValue,newVal));
    }

    public static void main(String[] args) {
        SimulatedCAS cas = new SimulatedCAS();
        cas.value=1;
        boolean b = cas.compareAndSet(1, 3);
        System.out.println(b);
        System.out.println(cas.value);
    }

}
