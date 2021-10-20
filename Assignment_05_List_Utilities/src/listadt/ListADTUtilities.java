package listadt;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/20
 */
public class ListADTUtilities {
    /**
     * Convert an array to listadt.ListADT
     */
    public static <T> ListADT<T> toList(T[] elements) {
        ListADT<T> result = new ListADTImpl<>();
        for (T ele : elements) {
            if (ele == null) {
                throw new IllegalArgumentException("Contains null element");
            }
            result.addBack(ele);
        }
        return result;
    }

    /**
     * Add elements to a target list
     */
    public static <T> void addAll(ListADT<T> list, T... elements) {
        for (T ele : elements) {
            if (ele == null) {
                throw new IllegalArgumentException("Contains null element");
            }
            list.addBack(ele);
        }
    }

    /**
     * returns the number of elements in the specified list equal to the specified element.
     * More formally, it should return the number of elements
     * in the list such that (o == null) ? e == null : o.equals(e))
     */
    public static <T> int frequency(ListADT<T> list, T element) {
        return list.frequency(element);
    }

    /**
     * returns true if the two lists have no elements in common.
     * This method should throw a IllegalArgumentException if either
     * list is null or if either list contains a null element.
     */
    public static <T> boolean disjoint(ListADT<?> one, ListADT<?> two) {
        if (one == null || two == null) {
            throw new IllegalArgumentException("null arguments");
        }
        return one.disjoint(two);
    }

    /**
     * returns true if the two lists are equal.
     * Two lists are equal if they have the same elements in the same order.
     * If either list is null, or if either list contains a null element, this
     * method should throw a IllegalArgumentException.
     */
    public static <T> boolean equals(ListADT<?> one, ListADT<?> two) {
        if (one == null || two == null) {
            throw new IllegalArgumentException("null arguments");
        }
        return one.toString().equals(two.toString());
    }


    /**
     * reverses the order of the elements in the specified list.
     */
    public static void reverse(ListADT<?> list) {
        list.reverse();
    }


    /**
     * swaps the elements at the specified position in the specified list.
     * This method should throw an IndexOutOfBoundsException if either i or j are out of range.
     */
    public static void swap(ListADT<?> list, int i, int j) {
        int size = list.getSize();
        if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (i == j) {
            return;
        }

    }


}
