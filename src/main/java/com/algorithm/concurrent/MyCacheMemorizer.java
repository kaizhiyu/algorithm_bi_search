package com.algorithm.concurrent;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 缓冲计算结果
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 */

public class MyCacheMemorizer implements ICompute{
    private final Map<Integer,Future<BigDecimal>> cache = new ConcurrentHashMap();
    private final ICompute c;
    public MyCacheMemorizer(ICompute c) {
        this.c = c;
    }

    @Override
    public BigDecimal compute(final int i) {
        Future<BigDecimal> f = cache.get(i);
        if (f ==null){
            //TODO to compute and put result into cache
            Callable<BigDecimal> callable = new Callable<BigDecimal>() {

                @Override
                public BigDecimal call() throws Exception {
                    return c.compute(i);
                }
            };

            FutureTask<BigDecimal> ft = new FutureTask<>(callable);
            cache.putIfAbsent(i,ft);
            if (f==null){
                f=ft;
                ft.run();
            }


            try {
                return  f.get();
            } catch (CancellationException e) {
                cache.remove(i,f);
                throw new RuntimeException();
            } catch (ExecutionException e) {
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return null;
    }


    class ComputeFutureTask implements Runnable {

        @Override
        public void run() {

        }
    }
}
