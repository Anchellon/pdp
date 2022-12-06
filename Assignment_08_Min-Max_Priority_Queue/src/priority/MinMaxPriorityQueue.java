package priority;

public interface MinMaxPriorityQueue<T> {
    void add(T element, int priority);

    T minPriorityElement();

    T maxPriorityElement();
}
