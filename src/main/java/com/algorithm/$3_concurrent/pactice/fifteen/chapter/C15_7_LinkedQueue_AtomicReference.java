package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fanhb on 2018/1/1
 * @desc xxxx
 */
public class C15_7_LinkedQueue_AtomicReference<E> {


    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> nextNode;

        public Node(E item, Node<E> nextNode) {
            this.item = item;
            this.nextNode = new AtomicReference<>(nextNode);
        }
    }

    private Node<E> dummy = new Node<>(null, null);
    private AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {

        Node<E> newNode = new Node<>(item, null);

        while (true) {

            Node<E> curTail = tail.get();
            Node<E> nextTail = curTail.nextNode.get();
            if (curTail == tail.get()) {
                if (nextTail != null) {
                    tail.compareAndSet(curTail, nextTail);
                } else {
                    if (curTail.nextNode.compareAndSet(null,newNode)){
                        tail.compareAndSet(curTail,newNode);
                        return true;
                    }
                }


            }
        }


    }

}
