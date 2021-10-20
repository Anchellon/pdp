import listadt.ListADT;
import listadt.ListADTUtilities;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void testReverse() {
        System.out.println(list.toString());
        ListADTUtilities.reverse(list);
        System.out.println(list.toString());
    }

    @Test
    public void testSwap() {
        list.swap(0, 1);
        System.out.println(list.toString());
    }

    @Test
    public void testDisjoint() {
        Integer[] arr = new Integer[]{2, 3, 4};
        ListADT<Integer> list2 = ListADTUtilities.toList(arr);
        System.out.println(ListADTUtilities.disjoint(list, list2));
    }
}