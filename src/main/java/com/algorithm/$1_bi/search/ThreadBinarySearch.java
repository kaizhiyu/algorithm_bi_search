package com.algorithm.$1_bi.search;

import com.algorithm.$5_json.JsonMapper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/18.
 * @version:v1.0
 */

public class ThreadBinarySearch {

    private static int ret = -1;
    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int[] arr = Test3.parallel(30);
        System.out.println(JsonMapper.toJson(arr));
        List<Integer> collect = Arrays.stream(arr).boxed().collect(toList());
        int key = 39;
        int i = collect.indexOf(key);
        System.out.println("ret: " + i);
        innerBinarySearch(arr, key, service);
    }

    public static int innerBinarySearch(int[] arr, int key, ExecutorService executor) {
        Future submit = service.submit(new SearchKey(0, arr.length, arr, key, executor));
        try {
            System.out.println("future: " + submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("future ret: " + ret);
        return -1;
    }

    static class SearchKey implements Callable {
        private int fromIndex;
        private int toIndex;
        private int key;
        private int[] arr;

        public SearchKey(int fromIndex, int toIndex, int[] arr, int key, Executor service) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.arr = arr;
            this.key = key;
        }


        @Override
        public Object call() throws Exception {
            if (toIndex >= fromIndex && toIndex< arr.length && fromIndex>=0) {
                int mid = (toIndex - fromIndex) / 2 + fromIndex;
                int value = arr[mid];
                if (key > value) {
                    return service.submit(new SearchKey(mid + 1, toIndex, arr, key, service)).get();
                } else if (key < value) {
                    return service.submit(new SearchKey(fromIndex, mid - 1, arr, key, service)).get();
                } else {
                    ret = mid;
                    return mid;
                }
            }
            return null;
        }
    }

}
