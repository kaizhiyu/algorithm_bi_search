package com.algorithm.$2_java8;

/**
 * volatile 测试，java8没什么用，也许java6能看出效果吧
 */
public class VolatileTest {
    private long foo;
    private volatile long bar;
    private static final long A = 0xffffffffffffffffl;
    private static final long B = 0;
    private int clock;
    public VolatileTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    foo = clock % 2 == 0 ? A : B;
                    bar = clock % 2 == 0 ? A : B;
                    clock++;
                }
            }

        }).start();


        while (true) {
            long fooRead = foo;
            if (fooRead != A && fooRead != B) {
                System.err.println("foo incomplete write " + Long.toHexString(fooRead));
            }
            long barRead = bar;
            if (barRead != A && barRead != B) {
                System.err.println("bar incomplete write " + Long.toHexString(barRead));
            }
        }
    }

    public static void main(String[] args) {
        new VolatileTest();
    }
}