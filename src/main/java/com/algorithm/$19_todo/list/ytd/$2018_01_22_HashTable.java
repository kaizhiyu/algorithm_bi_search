package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;
import com.algorithm.$8_annotation.single.ann.TODOList;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */
@SearchKeyWord("hashTable Load/Store")
public class $2018_01_22_HashTable {
    //TODO

    // 趣味数学书 送人

    // Load/Store指令
    // Load/Store指令用于寄存器和内存间数据的传送。
    // Load 用于把内存中的数据装载到寄存器中。
    // Store用于把寄存器中的数据存入内存。

    public static void main(String[] args) {
        Supplier<Supplier<String>> mutterfunktion = () -> {
            int container[] = {0};
            return () -> {
                container[0]++;
                return "Ich esse " + container[0] + " Kuchen.";
            };
        };
        Supplier<String> essen = mutterfunktion.get();
        System.out.println(essen.get());
        System.out.println(essen.get());
        System.out.println(essen.get());
    }

    @Test
    public void clojureStateSnapshotTest() {
        Function wrapperFunc = (a) -> {
            // final reference
            final WrapLong outerScopeState = new WrapLong();
            outerScopeState.aLong = System.currentTimeMillis();
            System.out.println("outer scope state BEFORE: " + outerScopeState.aLong);
            Function closure = (b) -> {
                System.out.println("closure: " + outerScopeState.aLong);
                return b;
            };

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outerScopeState.aLong = System.currentTimeMillis();
            System.out.println("outer scope state AFTER: " + outerScopeState.aLong);
            // show correct snapshot state
            closure.apply(new Object());
            return a;
        };
        // init clojure
        wrapperFunc.apply(new Object());


        Function<Integer, Object> fun = (b) -> null;
        Object apply = fun.apply(1);
        System.out.println("apply == " + apply);

    }

    public class WrapLong {
        public long aLong = 0;
    }

}
