import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListADTUtilities;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/20
 */
public class ListADTUtilitiesTest {

    ListADT<Integer> list;

    @Before
    public void setUp() {
        Integer[] arr = new Integer[]{9, 5, 1, 3, 6};
        list = ListADTUtilities.toList(arr);
    }

    @Test
    public void toList() {
        System.out.println(list.toString());
        list = new ListADTImpl<>();
        System.out.println(list.toString());
        ListADT<Integer> list2 = new ListADTImpl<>();
        System.out.println(ListADTUtilities.equals(list, list2));
    }

    @Test
    public void testReverse() {
        System.out.println(list.toString());
        ListADTUtilities.reverse(list);
        System.out.println(list.toString());
    }

    @Test
    public void testSwap() {
        ListADTUtilities.swap(list, 0, 1);
        System.out.println(list.toString());

        ListADTUtilities.swap(list, 0, 2);
        System.out.println(list.toString());

        ListADTUtilities.swap(list, 2, 3);
        System.out.println(list.toString());

        ListADTUtilities.swap(list, 3, 2);
        System.out.println(list.toString());
    }

    @Test
    public void testDisjoint() {
        Integer[] arr = new Integer[]{2, 3, 4};
        ListADT<Integer> list2 = ListADTUtilities.toList(arr);
        System.out.println(ListADTUtilities.disjoint(list, list2));
    }

    @Test
    public void testEquals() {
        Integer[] arr2 = new Integer[]{9, 5, 1, 3, 6};
        ListADT<Integer> list2 = ListADTUtilities.toList(arr2);
        assertTrue(ListADTUtilities.equals(list, list2));
    }

    @Test
    public void testAddAll() {
        ListADTUtilities.addAll(list, 7);
        System.out.println(list);

        ListADTUtilities.addAll(list, 8, 9);
        System.out.println(list);
    }

    @Test
    public void testFrequncy() {
        ListADTUtilities.addAll(list, 9);
        assertEquals(2, ListADTUtilities.frequency(list, 9));
        assertEquals(1, ListADTUtilities.frequency(list, 5));
        assertEquals(0, ListADTUtilities.frequency(list, 0));

    }
}