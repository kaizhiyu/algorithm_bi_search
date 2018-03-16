package com.question.q1_demo;

import jdk.nashorn.internal.ir.Block;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @auth v_fanhaibo on   2018/3/15
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                queue.put( p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                // TODO: 2018/3/15  允许退出线程
            }
        }
    }

    public void cancell(){
        interrupt();
    }
}
