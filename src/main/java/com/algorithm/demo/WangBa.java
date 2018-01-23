package com.algorithm.demo;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {
  
    private DelayQueue<Wangming> queue = new DelayQueue<Wangming>();
    public boolean yinye =true;  
      
    public void shangji(String userName,String identity,int money){
        Wangming man = new Wangming(userName,identity,1000*60*money+System.currentTimeMillis());
        System.out.println("网名"+man.getName()+" 身份证"+man.getIdentity()+"交钱"+money+"块,开始上机...");
        this.queue.add(man);  
    }  
      
    public void xiaji(Wangming man){  
        System.out.println("网名"+man.getName()+" 身份证号："+man.getIdentity()+"时间到下机...");
    }  
  
    @Override  
    public void run() {  
        // TODO Auto-generated method stub  
        while(yinye){  
            try {  
                System.out.println("检查ing");  
                Wangming man = queue.take();  
                xiaji(man);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String args[]){  
        try{  
            System.out.println("网吧开始营业");  
            WangBa siyu = new WangBa();  
            Thread shangwang = new Thread(siyu);  
            shangwang.start();  
              
            siyu.shangji("路人甲", "123", 10);
            siyu.shangji("路人乙", "234", 20);
            siyu.shangji("路人丙", "345", 10);
        }  
        catch(Exception ex){  
              
        }  
  
    }  
} 