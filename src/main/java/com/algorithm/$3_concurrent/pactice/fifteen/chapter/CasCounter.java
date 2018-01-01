package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import java.util.Objects;

/**
 * @author fanhb on 2018/1/1
 * @desc xxxx
 */
public class CasCounter {
    private SimulatedCAS cas = new SimulatedCAS();

    public int get() {
        return cas.get();
    }

    public void increment() {
        int oldValue;
        int newValue;

        do {
            oldValue = cas.get();
            newValue = oldValue + 1;
        } while (!cas.compareAndSet(oldValue, newValue));
    }

    public static void main(String[] args) {
        CasCounter casCounter = new CasCounter();
        for (int i = 0; i < 2000; i++) {
            incrementByThread(casCounter);


        }

    }


    public static void incrementByThread(CasCounter casCounter) {
        new Thread(() -> {
            casCounter.increment();
            System.out.println(casCounter.get());
        }, Objects.toString(casCounter.get()) + ": thread name.").start();
    }
}
