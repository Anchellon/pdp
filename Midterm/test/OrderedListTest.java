import Q1.OrderedList;
import Q1.impl.OrderedListImpl;
import org.junit.Test;

/**
 * @author novo
 * @since 2021/11/8
 */
public class OrderedListTest {
    @Test
    public void test() {
        OrderedList<Integer> list = new OrderedListImpl<>(3);
        list.add(1);
        System.out.println(list.toString());
        list.add(2);
        System.out.println(list.toString());
        list.add(3);
        System.out.println(list.toString());
        list.add(4);
        System.out.println(list.toString());
        list.add(1);
        System.out.println(list.toString());
        list.add(2);
        System.out.println(list.toString());
        list.add(3);
        System.out.println(list.toString());
        list.add(10);
        System.out.println(list.toString());
        list.add(-1);
        System.out.println(list.toString());

    }

    @Test
    public void testMerge() {
        OrderedList<Integer> list = new OrderedListImpl<>(5);
        list.add(10);
        System.out.println(list.toString());
        list.add(2);
        System.out.println(list.toString());
        list.add(4);
        System.out.println(list.toString());


        OrderedList<Integer> list2 = new OrderedListImpl<>(5);
        System.out.println(list2);
        list2.add(8);
        System.out.println(list2.toString());
        list2.add(3);
        System.out.println(list2.toString());
        list2.add(4);
        System.out.println(list2.toString());

        OrderedList<Integer> newList = list.merge(list2);
        System.out.println(newList.getCapacity());
        System.out.println(newList.getMax());
        System.out.println(newList);
        newList.add(7);
        newList.add(7);
        newList.add(7);
        newList.add(7);
        newList.add(7);
        newList.add(7);
        System.out.println(newList.toString());

        newList.resize(2);
        System.out.println(newList);
        newList.resize(1);
        System.out.println(newList);


    }
}
