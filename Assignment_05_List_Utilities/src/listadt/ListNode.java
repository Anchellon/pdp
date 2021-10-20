package listadt;

/**
 * @author novo
 * @since 2021/10/20
 */
public class ListNode<T> {
    public T val;
    public ListNode<T> prev;
    public ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
    }
}
