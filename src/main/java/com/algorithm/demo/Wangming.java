package com.algorithm.demo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangming implements Delayed {
  
    private String name;  
    //身份证  
    private String identity;
    //截止时间  
    private long endTime;  
      
    public Wangming(String name,String identity,long endTime){
        this.name=name;  
        this.identity =identity;
        this.endTime=endTime;  
    }  
      
    public String getName(){  
        return this.name;  
    }  
      
    public String getIdentity(){
        return this.identity;
    }  
      
    /** 
     * 用来判断是否到了截止时间 
     */  
    @Override  
    public long getDelay(TimeUnit unit) {
        // TODO Auto-generated method stub  
        return endTime-System.currentTimeMillis();  
    }  
  
    /** 
     * 相互批较排序用 
     */  
    @Override  
    public int compareTo(Delayed o) {  
        // TODO Auto-generated method stub  
        Wangming jia = (Wangming)o;  
        return endTime-jia.endTime>0?1:-1;
    }  
  
}  