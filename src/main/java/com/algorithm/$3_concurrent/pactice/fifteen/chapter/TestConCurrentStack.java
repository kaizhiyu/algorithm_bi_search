package com.algorithm.$3_concurrent.pactice.fifteen.chapter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fanhb on 2017/12/31
 * @desc xxxx
 */
public class TestConCurrentStack<E> {
    public static void main(String[] args) {
        TestConCurrentStack<Integer> stack = new TestConCurrentStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("======" + stack.pop() + ", count=" + stack.size());
        System.out.println("======" + stack.pop() + ", count=" + stack.size());
        System.out.println("======" + stack.pop() + ", count=" + stack.size());
        System.out.println("======" + stack.pop() + ", count=" + stack.size());
        System.out.println("======" + stack.pop() + ", count=" + stack.size());
        System.out.println("======" + stack.pop() + ", count=" + stack.size());

//        System.out.println(JsonMapper.toJson(stack));

    }

    public E pop() {
        Node<E> currData;
        Node<E> nextNode;
        do {
            currData = top.get();
            if (currData == null) return null;
            nextNode = currData.nextNode;
        } while (!top.compareAndSet(currData, nextNode));
        return currData.data;

    }

    public void push(E data) {
        Node<E> currData;
        Node<E> nextNode;
        do {
            nextNode = top.get();
            currData = new Node<>(data, nextNode == null ? 1 : nextNode.count + 1);
            currData.nextNode = nextNode;
        } while (!top.compareAndSet(nextNode, currData));

    }

    public boolean isEmpty() {
        return top.get() == null;
    }

    public int size() {
        Node<E> eNode = top.get();
        if (eNode != null) return eNode.count;
        return 0;
    }

    private AtomicReference<Node<E>> top = new AtomicReference<>();

    private static class Node<E> {
        final E data;
        Node<E> nextNode;
        final int count;

        public Node(E data, int count) {
            this.data = data;
            this.count = count;
        }
    }
}
