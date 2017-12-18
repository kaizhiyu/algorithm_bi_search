package com.algorithm.$2_java8.stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Title:xxxxx
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class Test4Stream {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.stream().map(biz->{
            System.out.println(biz.hashCode());
            return biz;
        }).collect(Collectors.toList());
        System.out.println(objects);
    }
}
