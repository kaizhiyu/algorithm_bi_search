package com.algorithm.$3_concurrent.pactice.fifteen.chapter.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/2.
 * @version:v1.0
 */

public class AddIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int i = 3;
        for (int j = 0; j < list.size(); j++) {
            list.add(++i);
            System.out.println("size = " + list.size() + ", add =" + (j+4));
        }
    }
}
