package listadt;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/20
 */
public class ListADTImpl<T> implements ListADT<T> {
    private ListNode<T> dummyHead;
    private ListNode<T> dummyTail;
    private int size = 0;

    /**
     * Constructor
     */
    public ListADTImpl() {
        dummyHead = new ListNode<>(null);
        dummyTail = new ListNode<>(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    @Override
    public void addFront(T val) {
        ListNode<T> first = dummyHead.next;
        ListNode<T> newNode = new ListNode<>(val);
        dummyHead.next = newNode;
        newNode.prev = dummyHead;
        newNode.next = first;
        first.prev = newNode;
        size++;
    }

    @Override
    public boolean disjoint(ListADT<? extends T> o) {
        Set<T> valueSet = getValueSet();
        ListNode<? extends T> temp = o.getDummyHead().next;
        while (temp != o.getDummyTail()) {
            if (valueSet.contains(temp.val)) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    /**
     * add node to tail
     */
    @Override
    public void addBack(T val) {
        ListNode<T> tail = dummyTail.prev;
        ListNode<T> newNode = new ListNode<>(val);
        tail.next = newNode;
        newNode.prev = tail;
        newNode.next = dummyTail;
        dummyTail.prev = newNode;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListNode<T> temp = dummyHead.next;
        while (temp != dummyTail) {
            s.append(temp.val).append("\t");
            temp = temp.next;
        }
        return s.toString();
    }

    /**
     * get the frequency of a val in this list
     */
    public int frequency(T val) {
        if (val == null) {
            throw new IllegalArgumentException("Null argument");
        }
        ListNode<T> temp = dummyHead.next;
        int count = 0;
        while (temp != dummyTail) {
            if (temp.val == val) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    /**
     * Get a Set of all values in this list
     */
    public Set<T> getValueSet() {
        Set<T> set = new HashSet<>();
        ListNode<T> temp = dummyHead.next;
        while (temp != dummyTail) {
            set.add(temp.val);
            temp = temp.next;
        }
        return set;
    }


    /**
     * Get a reversed list of this list
     */
    public void reverse() {
        ListADT<T> result = new ListADTImpl<>();
        ListNode<T> temp = dummyTail.prev;
        while (temp != dummyHead) {
            result.addBack(temp.val);
            temp = temp.prev;
        }
        this.dummyHead = result.getDummyHead();
        this.dummyTail = result.getDummyTail();
    }

    /**
     * swap two elements at i and j
     */
    public void swap(int i, int j) {
        if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (i == j) {
            return;
        }
        // make sure i < j
        if (i > j) {
            swap(j, i);
            return;
        }
        // get i
        ListNode<T> node1 = null, node2 = null;
        ListNode<T> temp = dummyHead.next;
        int count = 0;
        while (temp != dummyTail) {
            if (count == i) {
                node1 = temp;
            }
            if (count == j) {
                node2 = temp;
            }
            if (node1 != null && node2 != null) {
                break;
            }
            count++;
            temp = temp.next;
        }
        // check if they are adjacent or not
        if (node1.next == node2) {
            swapAdjNode(node1, node2);
        } else {
            swapNode(node1, node2);
        }
    }

    private void swapNode(ListNode<T> node1, ListNode<T> node2) {
        ListNode<T> prevOfNode1 = node1.prev;
        ListNode<T> nextOfNode1 = node1.next;

        ListNode<T> prevOfNode2 = node2.prev;
        ListNode<T> nextOfNode2 = node2.next;

        // change node2
        prevOfNode1.next = node2;
        node2.prev = prevOfNode1;
        node2.next = nextOfNode1;
        nextOfNode1.prev = node2;

        // change node1
        prevOfNode2.next = node1;
        node1.prev = prevOfNode2;
        node1.next = nextOfNode2;
        nextOfNode2.prev = node1;
    }

    private void swapAdjNode(ListNode<T> node1, ListNode<T> node2) {
        ListNode<T> prevOfNode1 = node1.prev;
        ListNode<T> nextOfNode2 = node2.next;

        // change node1
        prevOfNode1.next = node2;
        node2.prev = prevOfNode1;

        // change node2
        nextOfNode2.prev = node1;
        node1.next = nextOfNode2;

        node2.next = node1;
        node1.prev = node2;
    }


    public ListNode<T> getDummyHead() {
        return dummyHead;
    }

    public ListNode<T> getDummyTail() {
        return dummyTail;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ListNode<T> temp = dummyHead.next;
        while (i-- != 0) {
            temp = temp.next;
        }
        return temp.val;
    }

    public int getSize() {
        return size;
    }
}
