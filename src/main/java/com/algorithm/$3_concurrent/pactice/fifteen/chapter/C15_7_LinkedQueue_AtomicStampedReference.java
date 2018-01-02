package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$8_annotation.ParaDesc;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/2.
 * @version:v1.0
 */

public class C15_7_LinkedQueue_AtomicStampedReference<E> {
    private static class Node<E> {
        final E item;
        final AtomicStampedReference<Node<E>> nextNode;

        public Node(E item, Node<E> reference) {
            this.item = item;
            this.nextNode = new AtomicStampedReference<>(reference, 0);
        }
    }

    /**
     * method of Queue
     */
    private Node dummy = new Node<E>(null, null);
    private AtomicStampedReference<Node<E>> head = new AtomicStampedReference<>(dummy, 0);
    private AtomicStampedReference<Node<E>> tail = new AtomicStampedReference<>(dummy, 0);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        Node<E> curReference = tail.getReference();
        int curStamp = tail.getStamp();
        Node<E> nextNode = curReference.nextNode.getReference();
        if (curReference == tail.getReference() && curStamp == tail.getStamp()) {
            if (nextNode != null) {
                tail.compareAndSet(curReference, nextNode, curStamp, curStamp + 1);
            } else {
                if (curReference.nextNode.compareAndSet(null, newNode, 0, 1)) {
                    tail.compareAndSet(curReference, newNode, curStamp, curStamp + 1);
                }
            }
        }

//        do {
//            stamp = tail.getStamp();
//            ref = tail.getReference();
//        }while (tail.compareAndSet(ref,newNode,stamp,stamp+1));
        while (true) {


        }

    }


}
