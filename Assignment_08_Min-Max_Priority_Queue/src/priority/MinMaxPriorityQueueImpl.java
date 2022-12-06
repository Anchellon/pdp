package priority;

import java.util.*;

public class MinMaxPriorityQueueImpl<T> implements MinMaxPriorityQueue<T>{
    private TreeMap<Integer, Queue<T>> map;
    public MinMaxPriorityQueueImpl() {
        map = new TreeMap<>();
    }

    @Override
    public void add(T element, int priority) {
        if (!map.containsKey(priority)) {
            map.put(priority, new LinkedList<>());
        }
        map.get(priority).offer(element);
    }

    @Override
    public T minPriorityElement() {
        if (map.isEmpty()) {
            return null;
        }
        int minPriority = map.firstKey();
        T result = map.get(minPriority).poll();
        if (map.get(minPriority).isEmpty()) {
            map.remove(minPriority);
        }
        return result;
    }

    @Override
    public T maxPriorityElement() {
        if (map.isEmpty()) {
            return null;
        }
        int maxPriority = map.lastKey();
        T result = map.get(maxPriority).poll();
        if (map.get(maxPriority).isEmpty()) {
            map.remove(maxPriority);
        }
        return result;
    }
}
