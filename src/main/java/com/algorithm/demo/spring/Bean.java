package com.algorithm.demo.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装配置文件中的bean节点
 * @author 周君
 */
public class Bean {

    private String id;
    private String className;
    private List<Property> properties = new ArrayList<Property>();//bean节点下可以有多个property节点

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<Property> getProperties() {
        return properties;
    }
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
    @Override
    public String toString() {
        return "Bean [id=" + id + ", className=" + className
                + ", properties=" + properties + "]";
    }

}