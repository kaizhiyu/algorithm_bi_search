package com.algorithm.demo;

import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Arrays;

public class QuickSort2 {

    static int CUTOFF = 10;
//    static int CUTOFF = 0;

    public static void quickSort(int[] arr, int left, int right) {
        if ((left + CUTOFF) <= right) {

            int pivot = media3(arr, left, right);
            int i = left, j = right - 1;

            for (; ; ) {
                while (arr[++i] < pivot) {
                }
                while (arr[--j] > pivot) {
                }
                if (i < j) {
                    swapElement(arr, left, right);
                } else {
                    break;
                }
            }
            swapElement(arr, i, right - 1);
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);

        } else {
            // TODO: 2018/3/7
            insertSort(arr);
        }

    }

    public static int media3(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[mid] < arr[left])
            swapElement(arr, left, mid);
        if (arr[right] < arr[left])
            swapElement(arr, left, right);
        if (arr[mid] < arr[right])
            swapElement(arr, mid, right);

        swapElement(arr, mid, right - 1);
        return arr[right - 1];
    }

    public static void swapElement(int[] arr, int ele1, int ele2) {
        int temp = arr[ele1];
        arr[ele1] = arr[ele2];
        arr[ele2] = temp;
    }

    private static int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                System.out.println(Arrays.toString(arr));
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return arr;
    }

    private static void insertSort2(int[] arr) {
        int j;
        for (int p = 1; p < arr.length; p++) {
            int tmp = arr[p];
            for (j = p; j > 0 && tmp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static void insertSort3(int[] arr) {
        int j;
        for (int p = 1; p < arr.length; p++) {
            int tmp = arr[p];
            for (j = p; j > 0 && tmp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

//    /**
//     * BEGIN LAYOUTLIB CHANGE
//     * This is a custom version that doesn't use the non standard LinkedHashMap#eldest.
//     * END LAYOUTLIB CHANGE
//     * <p>
//     * A cache that holds strong references to a limited number of values. Each time
//     * a value is accessed, it is moved to the head of a queue. When a value is
//     * added to a full cache, the value at the end of that queue is evicted and may
//     * become eligible for garbage collection.
//     * <p>
//     * <p>If your cached values hold resources that need to be explicitly released,
//     * override {@link #entryRemoved}.
//     * <p>
//     * <p>If a cache miss should be computed on demand for the corresponding keys,
//     * override {@link #create}. This simplifies the calling code, allowing it to
//     * assume a value will always be returned, even when there's a cache miss.
//     * <p>
//     * <p>By default, the cache size is measured in the number of entries. Override
//     * {@link #sizeOf} to size the cache in different units. For example, this cache
//     * is limited to 4MiB of bitmaps:
//     * <pre>   {@code
//     *   int cacheSize = 4 * 1024 * 1024; // 4MiB
//     *   LruCache<String, Bitmap> bitmapCache = new LruCache<String, Bitmap>(cacheSize) {
//     *       protected int sizeOf(String key, Bitmap value) {
//     *           return value.getByteCount();
//     *       }
//     *   }}</pre>
//     * <p>
//     * <p>This class is thread-safe. Perform multiple cache operations atomically by
//     * synchronizing on the cache: <pre>   {@code
//     *   synchronized (cache) {
//     *     if (cache.get(key) == null) {
//     *         cache.put(key, value);
//     *     }
//     *   }}</pre>
//     * <p>
//     * <p>This class does not allow null to be used as a key or value. A return
//     * value of null from {@link #get}, {@link #put} or {@link #remove} is
//     * unambiguous: the key was not in the cache.
//     * <p>
//     * <p>This class appeared in Android 3.1 (Honeycomb MR1); it's available as part
//     * of <a href="http://developer.android.com/sdk/compatibility-library.html">Android's
//     * Support Package</a> for earlier releases.
//     */
    public static void main(String[] args) {
//        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext("spring.xml");
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 1, 7, 9, 5, 3, 0, 3, 6};
//        quickSort(a, 0, a.length - 1);
        insertSort2(a);
        System.out.println(Arrays.toString(a));
    }
}