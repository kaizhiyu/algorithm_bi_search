package com.algorithm.demo.spring;

import java.util.Map;
import java.util.Map.Entry;


public class Test {

    public static void main(String[] args) {
        testIOC();
        //testConfig();
    }
    /**
     * 测试IOC容器
     */
    private static void testIOC(){

        BeanFactory bf = new ClassPathXmlApplicationContext("/demo.xml");

        User user = (User) bf.getBean("user");
        System.out.println(user);
        Address address = (Address) bf.getBean("address");
        System.out.println(address);
    }
    /**
     * 测试读取配置文件
     */
    private static void testConfig(){
        Map<String,Bean> map = XmlConfig.getConfig("/ApplicationContext.xml");
        for (Entry<String, Bean> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"==="+entry.getValue());
        }
    }

}