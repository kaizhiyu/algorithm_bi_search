package com.algorithm.concurrent;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓冲计算结果
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class MyCacheMemorizer implements ICompute{
    private final Map<Integer,BigDecimal> cache = new ConcurrentHashMap();


    @Override
    public BigDecimal compute(int i) {
        BigDecimal bigDecimal = cache.get(i);
        if (bigDecimal ==null){
            //TODO to compute and put result into cache
            bigDecimal = new BigDecimal(22);
            cache.put(i,bigDecimal);
            try {
                Thread.sleep(1000L);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return  bigDecimal;
    }
}
