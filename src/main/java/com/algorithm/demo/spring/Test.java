package com.algorithm.demo.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Test {

    public static void main(String[] args) throws DocumentException, IOException {
//        testIOC();
        //testConfig();
        //查看 classpath下的xml文件是否存在
        InputStream in = ClassLoader.getSystemResourceAsStream("com/algorithm/demo/spring/demo.xml");
//        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/algorithm/demo/spring/demo.xml");
//        InputStream in = Test.class.getClassLoader().
//        URL resource = Thread.currentThread().getContextClassLoader().getResource("demo.xml");
//        InputStream in = resource.openStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        System.out.println(document.getRootElement());
        List<Element> elements = document.getRootElement().elements();
        elements.stream().forEach(System.out::println);


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