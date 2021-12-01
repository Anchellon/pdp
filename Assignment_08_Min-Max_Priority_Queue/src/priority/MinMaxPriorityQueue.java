package priority;

/**
 * @author novo
 * @since 2021/11/29
 */
public interface MinMaxPriorityQueue<T> {
    void add(T item, int priority);

    T minPriorityItem();

    T maxPriorityItem();
}
