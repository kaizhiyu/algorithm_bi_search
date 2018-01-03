package com.algorithm.$1_bi.search.hash;

import com.algorithm.$8_annotation.Doc4Desc;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/3.
 * @version:v1.0
 */

@Doc4Desc("hashMap.getNode(int hash,Object key)")
public class HashMap_Method {

    //    final Node<K, V> getNode(int hash, Object key) {
    //
    //
    //        Node<K, V>[] tab;
    //        Node<K, V> first, e;
    //        int n;
    //        K k;
    //
    //        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
    //            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k)))) return first;
    //            if ((e = first.next) != null) {
    //                if (first instanceof TreeNode) return ((TreeNode<K, V>) first).getTreeNode(hash, key);
    //                do {
    //                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) return e;
    //                } while ((e = e.next) != null);
    //            }
    //
    //        }
    //        return null;
    //    }

    public static void main(String[] args) {
        int n ;
        System.out.println((n = 3) > 1);
    }
}
