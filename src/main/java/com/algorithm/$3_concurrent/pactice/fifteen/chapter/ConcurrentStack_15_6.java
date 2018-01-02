package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import com.algorithm.$8_annotation.CompareAndSwap;
import com.algorithm.$8_annotation.ThreadSafe;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fanhb on 2017/12/31
 * @desc xxxx
 */

@ThreadSafe
public class ConcurrentStack_15_6<E> implements Serializable {
    @CompareAndSwap
    private AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E data) {
        Node<E> newNode = new Node<>();
        Node<E> oldVal;
        do {
            oldVal = top.get();
            newNode.setData(data);
            newNode.setNextNode(oldVal);

        } while (!top.compareAndSet(oldVal, newNode));

    }

    public E pop() {
        Node<E> oldVal;
        Node<E> nextNode;
        do {
            oldVal = top.get();
            if (oldVal == null) {
                return null;
            }
            nextNode = oldVal.getNextNode();
        } while (!top.compareAndSet(oldVal, nextNode));
        return oldVal.getData();
    }


    private static class Node<E> implements Serializable {
        private E data;
        private Node<E> nextNode;

        //GETTER SETTER
        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }


}
