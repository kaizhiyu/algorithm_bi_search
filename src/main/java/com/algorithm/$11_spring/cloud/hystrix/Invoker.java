package com.algorithm.$11_spring.cloud.hystrix;

/**
 * @auth v_fanhaibo on   2018/1/30
 */
public class Invoker {

    Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(){
        command.execute();
    }

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        HystrixCommandDemo hystrixCommandDemo = new HystrixCommandDemo(receiver);
        Invoker invoker = new Invoker(hystrixCommandDemo);
        invoker.action();


    }
}
