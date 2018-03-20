package com.algorithm.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.HashMap;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

    @Autowired
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.get("ss");

        int n = 2;
//        ThreadPoolTaskExecutor
        SpringApplication.run(DemoApplication.class, args);
    }


}
