package listadt;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/20
 */
public interface ListADT<T> {
    String toString();

    int frequency(T val);

    int getSize();

    Set<T> getValueSet();

    void reverse();

    void swap(int i, int j);

    ListNode<T> getDummyHead();

    ListNode<T> getDummyEnd();

    T get(int i);

    void addBack(T val);

    void addFront(T val);

    boolean disjoint(ListADT<?> o);
}
