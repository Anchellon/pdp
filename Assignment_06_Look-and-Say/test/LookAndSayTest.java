import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author novo
 * @since 2021/11/11
 */
public class LookAndSayTest {

    @Test()
    public void testDefaultSeed() {
        Iterator<BigInteger> iterator = new LookAndSayIterator();
        BigInteger previous = new BigInteger("1");
        iterator.next(); // burn off the first one
        // 11
        //
        while (iterator.hasNext()) {
            BigInteger b = iterator.next();
            System.out.println(b);
            previous = b;
        }
        previous = iterator.next();
        System.out.println(previous.toString().length());
        assertTrue("The iterator ended but the last number was not more than " + "100 digits long",
                previous.toString().length() > 100);
    }

    @Test
    public void testGoBack() {
        RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("123"));
        System.out.println(iterator.next()); // return 1, curr 11
        System.out.println(iterator.next()); // return 11, curr 21 111213
        System.out.println(iterator.next()); // return 21, curr 1211  31121113
        System.out.println(iterator.prev()); // return 1211, curr 21 111213
        System.out.println(iterator.prev()); // return 21, curr 11 123
        System.out.println(iterator.prev()); // return 11, curr 1
        System.out.println(iterator.prev()); // return 1, curr 1
        System.out.println(iterator.hasPrevious());



    }
}
