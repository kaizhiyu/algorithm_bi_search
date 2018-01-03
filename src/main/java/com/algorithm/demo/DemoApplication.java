package com.algorithm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.get("ss");

        int n = 2;

        SpringApplication.run(DemoApplication.class, args);
    }


}
