package Q1.impl;

import Q1.ListNode;
import Q1.OrderedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/11/8
 */
public class OrderedListImpl<E extends Comparable<E>> implements OrderedList<E> {

    private ListNode<E> dummyHead;
    private ListNode<E> dummyTail;
    private int size;
    private int capacity;

    /**
     * Constructor
     *
     * @param capacity capacity of this list
     */
    public OrderedListImpl(int capacity) {
        // check capacity
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be positive");
        }
        this.capacity = capacity;
        this.size = 0;
        dummyHead = new ListNode<>(null);
        dummyTail = new ListNode<>(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    @Override
    public E getMax() {
        // if empty, return null
        if (size == 0) {
            return null;
        }
        // return the last node in the list
        return dummyTail.prev.val;
    }

    @Override
    public void add(E val) {
        // check parameter
        if (val == null) {
            throw new IllegalArgumentException("new value cannnot be null");
        }
        // if this list still have free place
        if (size == capacity && val.compareTo(dummyHead.next.val) > 0) {
            // check if new val is bigger than current minimum val or not
            // if bigger, then firstly, remove first node
            removeFirst();
        }
        if (size < capacity) {
            // if we have empty space, then addNode
            addNode(val);
        }
    }

    private void addNode(E val) {
        // get possible insert place
        ListNode<E> prev = getPossibleInsertPlace(val);
        // get previous followed node
        ListNode<E> followedNode = prev.next;
        // create new node
        ListNode<E> newNode = new ListNode<>(val);

        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = followedNode;
        followedNode.prev = newNode;
        size++;
    }

    @Override
    public OrderedList<E> merge(OrderedList<E> other) {
        // get capacity for new list
        int newCapacity = this.capacity + other.getCapacity();
        // new a list
        OrderedListImpl<E> newList = new OrderedListImpl<>(newCapacity);
        // start add nodes to new List
        ListNode<E> temp = this.dummyHead.next;
        while (temp != this.dummyTail) {
            newList.add(temp.val);
            temp = temp.next;
        }
        // add nodes from other
        temp = other.getDummyHead().next;
        while (temp != other.getDummyTail()) {
            newList.add(temp.val);
            temp = temp.next;
        }
        return newList;
    }

    @Override
    public void resize(int newCapacity) {
        if (newCapacity <= 0) {
            throw new IllegalStateException("capacity should be positive");
        }
        // modify capacity
        this.capacity = newCapacity;
        // if new capacity is smaller than current capacity
        while (size > newCapacity) {
            // remove some nodes
            removeFirst();
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public ListNode<E> getDummyHead() {
        return this.dummyHead;
    }

    @Override
    public ListNode<E> getDummyTail() {
        return this.dummyTail;
    }

    @Override
    public void addAll(OrderedList<E> other) {
        ListNode<E> temp = other.getDummyHead().next;
        while (temp != other.getDummyTail()) {
            this.add(temp.val);
            temp = temp.next;
        }
    }

    /**
     * find the largest element that is smaller than new val
     * @param val new val
     */
    private ListNode<E> getPossibleInsertPlace(E val) {
        if (size == 0) {
            return dummyHead;
        }
        // start searching from head
        ListNode<E> temp = dummyHead;
        // if next value is smaller than new value, keep moving
        while (temp.next != dummyTail && val.compareTo(temp.next.val) > 0) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * remove first node in the list
     */
    private void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("No values exists");
        }
        ListNode<E> newFirst = dummyHead.next.next;
        dummyHead.next = newFirst;
        newFirst.prev = dummyHead;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode<E> temp = dummyHead.next;
        while (temp != dummyTail) {
            res.append(temp.val).append(" ");
            temp = temp.next;
        }
        for (int i = 0; i < capacity - size; i++) {
            res.append("?").append(" ");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
