package com.algorithm.$2_java8.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

/**
 * Title:实现自己的收集器
 *
 * @author:v_fanhaibo on 2017/12/7.
 * @version:v1.0
 *  new CollectorImpl<>(
 *  1: (Supplier<List<T>>) ArrayList::new,
 *  2:  List::add,
 *  3: (left, right) -> { left.addAll(right); return left; },
 *  4: CH_ID);
 */
//
//
public class MyListCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * <T> - input elements
     * <A> - the mutable accumulation type of the reduction operation (often hidden as an implementation detail)
     * <R> - the result type of the reduction operation
     */
    @Override
    public Supplier supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>,T> accumulator() {
        return (list,item)->list.add(item);
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function finisher() {
        return i->i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
