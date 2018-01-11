package com.algorithm.$17_data.structures.algorithm;

import com.algorithm.$8_annotation.single.ann.Doc4Desc;

/**
 * @author fanhb on 2018/1/10
 * @desc xxxx
 */

@Doc4Desc("数组,链表的时间复杂度")
public class $3_1_OLog_n {

    /**
     *                     查找     插入      删除

     数组            o(n)        o(1)        o(n)

     有序数组     o(lgn)      o(n)        o(n)

     链表            o(n)        o(1)       o(n)

     有序链表      o(n)        o(n)        o(n)

     二叉树最坏   o(n)        o(n)        o(n)

     二叉树一般   o(lgn)     o(lgn)      o(lgn)

     平衡树         o(lgn)     o(lgn)      o(lgn)

     哈希表         o(1)        o(1)        o(1)



     (1)向一个有序数组中插入一个数的时间复杂度是多少?

     查找插入位置如果用遍历查找的是O(n)，用二分查找是O(log2n)。

     但是数组的插入操作需要将插入位置后的元素全部后移一位，这需要O(n)。

     所以总的时间复杂度是O(n)。（O(n)+O(n)=O(n)，O(log2n)+O(n)=O(n)）

     (2) 有序链表查找的时间复杂度是O(n)的原因是什么？

     折半查找对链表而言根本不能达到O(logN)的效率。只有当访问集合中任何一个元素的时间是常量O(1)时间时，折半查找才能达到O(logN)，而链表访问其中元素的平均时间是O(N)即线性时间。对用数组构造的集合才能使用折半查找。
     */
}
