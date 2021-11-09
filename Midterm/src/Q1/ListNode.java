package Q1;

/**
 * @author novo
 * @since 2021/11/8
 */
public class ListNode<E> {
    public E val;
    public ListNode<E> prev;
    public ListNode<E> next;

    public ListNode(E val) {
        this.val = val;
    }
}

