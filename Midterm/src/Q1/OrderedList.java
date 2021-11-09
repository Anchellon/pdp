package Q1;

public interface OrderedList<E extends Comparable<E>> {
    // Return the maximum element in the list.
    // It returns null if the list is empty.
    E getMax();

    // Add `val` to the ordered list.
    void add(E val);

    // Merge `other` and this list.
    // Returns a new list.
    OrderedList<E> merge(OrderedList<E> other);

    // Reset the capacity to `newCapacity`.
    // Truncate the smallest elements if length is larger than `newCapacity`.
    void resize(int newCapacity);

    int getCapacity();

    ListNode<E> getDummyHead();

    ListNode<E> getDummyTail();

    void addAll(OrderedList<E> other);
}