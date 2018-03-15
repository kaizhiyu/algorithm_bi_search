package com.question.q1_demo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @auth v_fanhaibo on   2018/3/14
 */
public class MyLRU<K, V> {

    class Node<K, V> {
        Node pre;
        Node next;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    int cap;
    Node head;
    Node tail;
    Map<K, Node<K, V>> cache = new HashMap<>();

    public MyLRU(int capacity) {
        cap = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    public void set(K key, V val) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.key = key;
            node.value = val;
            cache.put(key, node);

            moveNode(node);
            putNode2TailPre(node);
        }

        if (cache.size() == cap) {
            removeFirstNode();
        }

        Node<K, V> newNode = new Node<>(key, val);
        cache.put(key, newNode);
        putNode2TailPre(newNode);

    }

    public Node<K, V> removeFirstNode() {
        Node<K, V> next = head.next;
        cache.remove(head.next.key);
        moveNode(head.next);
        next.pre = null;
        next.next = null;
        return next;
    }

    public void moveNode(Node<K, V> target) {
        //把断开的连接拼接起来1<=>2<=>3  ---> 2<=>3
        target.next.pre = target.pre;
        target.pre.next = target.next;
    }

    public void putNode2TailPre(Node<K, V> n) {
        n.pre = tail.pre;
        n.next = tail;

        tail.pre.next = n;
        tail.pre = n;
    }


}

class MyLRUCacheLinkedHashMap<K, V> {
    public Map<K, V> cacheMap;
    private int capacity;
    private boolean accessOrder;
    private float FACTOR = 0.75f;

    public MyLRUCacheLinkedHashMap(int capacity) {
        this(capacity, true);
    }

    public MyLRUCacheLinkedHashMap(int capacity, boolean accessOrder) {
        this.capacity = capacity;
        this.accessOrder = accessOrder;
        this.cacheMap = new LinkedHashMap<K, V>(capacity, FACTOR, accessOrder) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public void set(K key, V val) {
        cacheMap.put(key, val);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }
}

class LRUCacheLinkedHashMap {
    private int capacity;
    public Map<Integer, Integer> cache;

    public LRUCacheLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.cache = new java.util.LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            // 定义put后的移除规则，大于容量就删除eldest
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }

        };
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else
            return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) throws InterruptedException {
        MyLRUCacheLinkedHashMap lruCache = new MyLRUCacheLinkedHashMap(10);

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(1000L);
            lruCache.set(i % 10, i);

            System.out.print("json: " );
            Set<Map.Entry<Integer, Integer>> entries = lruCache.cacheMap.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                System.out.print(entry.getValue() +" ");
            }
            System.out.println();
        }
    }
}


