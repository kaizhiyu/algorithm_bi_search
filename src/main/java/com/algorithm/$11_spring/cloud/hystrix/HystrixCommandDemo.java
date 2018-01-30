package com.algorithm.$11_spring.cloud.hystrix;

/**
 * @auth v_fanhaibo on   2018/1/30
 */
public class HystrixCommandDemo implements Command{

    Receiver receiver;
    public HystrixCommandDemo(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void execute() {
        this.receiver.action();
        System.out.println(" command handle receiver...");

    }
}
