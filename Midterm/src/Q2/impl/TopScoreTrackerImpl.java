package Q2.impl;

import Q1.ListNode;
import Q1.OrderedList;
import Q1.impl.OrderedListImpl;
import Q2.Run;
import Q2.TopScoreTracker;

/**
 * @author novo
 * @since 2021/11/8
 */
public class TopScoreTrackerImpl implements TopScoreTracker {
    // top N each month
    private int N;
    // top M for the year
    private int M;
    // use an orderedList array to store monthly records
    private OrderedList<Run>[] monthlyRecords;

    /**
     * constructor
     */
    public TopScoreTrackerImpl(int N, int M) {
        if (N <= 0 || M <= 0) {
            throw new IllegalArgumentException("arguments should be positive");
        }
        this.N = N;
        this.M = M;
        // init array
        monthlyRecords = new OrderedList[12];
        for (int i = 0; i < 12; i++) {
            monthlyRecords[i] = new OrderedListImpl<>(N);
        }
    }

    @Override
    public void addRun(int m, Run r) {
        if (m < 0 || m > 11) {
            throw new IllegalArgumentException("m should between 0 to 11");
        }
        if (r == null) {
            throw new IllegalArgumentException("run could not be null");
        }
        monthlyRecords[m].add(r);
    }

    @Override
    public String annualReport() {
        // use a mergeResult to store the result
        OrderedList<Run> mergeResult = new OrderedListImpl<>(M);
        for (int i = 0; i < 12; i++) {
            mergeResult.addAll(monthlyRecords[i]);
        }
        // return result
        StringBuilder stringResult = new StringBuilder();
        stringResult.append("[");

        // iterate over all results in the merge result
        ListNode<Run> temp = mergeResult.getDummyHead().next;
        while (temp != mergeResult.getDummyTail()) {
            Run currRun = temp.val;
            stringResult.append('(').append(currRun.getId()).append(", ").append(currRun.getScore()).append(") ");
            temp = temp.next;
        }
        // if we have at least one data, remove the last space
        if (stringResult.length() > 1) {
            stringResult.deleteCharAt(stringResult.length() - 1);
        }
        stringResult.append("]");
        return stringResult.toString();
    }
}
