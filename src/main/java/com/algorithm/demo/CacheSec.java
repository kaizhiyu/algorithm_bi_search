package com.algorithm.demo;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheSec<K, V> {
    private static final Logger LOG = Logger.getLogger(CacheSec.class.getName());

    private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();

    private static DelayQueue<DelayItem<Pair>> q = new DelayQueue<>();





    // 添加缓存对象
    public void put(K key, V value, long time, TimeUnit unit) {
        V oldValue = cacheObjMap.put(key, value);
        if (oldValue != null)
            q.remove(key);
        q.put(new DelayItem<>(new Pair<>(key, value), time,unit));
    }

    public V get(K key) {

        try {
            DelayItem<Pair> take = q.take();
           if (take != null){
               Pair<K, V> item = take.getItem();
               cacheObjMap.remove(item.first,item.second);
           }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cacheObjMap.get(key);
    }

    // 测试入口函数
    public static void main(String[] args) throws Exception {
//        CacheSec<Integer, String> cache = new CacheSec<Integer, String>();
//        cache.put(1, "aaaa", 3, TimeUnit.SECONDS);
//
////        Thread.sleep(1000 * 2);
//        {
//            String str = cache.get(1);
//            System.out.println(str);
//        }
//
////        Thread.sleep(1000 * 2);
//        {
//            String str = cache.get(1);
//            System.out.println(str);
//        }

        newIns(1,"a",1000000000);
//        newIns(2,"b",20);
        while (true){
            long time = new Date().getTime();
            DelayItem<Pair> take = q.take();
            Pair item = take.getItem();
            System.out.println((new Date().getTime() - time)/1000);
            System.out.println(item);

        }


    }

    public static void newIns(Integer key,String value,Integer timeSeconds){
        q.put( new DelayItem<Pair>(new Pair<>(key, value), timeSeconds,TimeUnit.SECONDS));

    }

}