package bignumber;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * @author novo
 * @since 2021/10/7
 */
public class BigNumberImplTest {

    BigNumber number;

    @Before
    public void setUp() throws Exception {
        // default 32411
        number = new BigNumberImpl("32411");
    }

    @Test
    public void length() {
        assertEquals(5, number.length());
    }

    @Test
    public void shiftLeft() {
        number.shiftLeft(1);
        assertEquals(6, number.length());
    }

    @Test
    public void shiftRight() {
        number.shiftRight(1);
        assertEquals(4, number.length());

        number.shiftRight(1000);
        assertEquals(1, number.length());
    }

    @Test
    public void addDigit() {
        number.addDigit(8);
        assertEquals(9, number.getDigitAt(0));

        number.addDigit(2);
        assertEquals(1, number.getDigitAt(0));
        assertEquals(2, number.getDigitAt(1));

    }

    @Test
    public void getDigitAt() {
        assertEquals(1, number.getDigitAt(0));
    }

    @Test
    public void copy() {
        BigNumber copy = number.copy();
        assertEquals(5, copy.length());
    }

    @Test
    public void add() {
        BigNumberImpl bigNumber = new BigNumberImpl("100000");
        BigNumber result = number.add(bigNumber);
        assertEquals(6, result.length());
    }

    @Test
    public void compareTo() {
//        BigInteger int1 = new BigInteger("10");
//        BigInteger int2 = new BigInteger("20");
//        System.out.println(int1.compareTo(int2));
//
//        int1 = new BigInteger("20");
//        System.out.println(int1.compareTo(int2));
//
//        int1 = new BigInteger("30");
//        System.out.println(int1.compareTo(int2));

        BigNumber bigNumber = new BigNumberImpl("32222");
        assertEquals(1, number.compareTo(bigNumber));

        BigNumber bigNumber2 = new BigNumberImpl("32411");
        assertEquals(0, number.compareTo(bigNumber2));

        BigNumber bigNumber3 = new BigNumberImpl("1");
        assertEquals(1, number.compareTo(bigNumber3));

        System.out.println(number.compareTo(new BigNumberImpl("0")));

    }

    @Test
    public void testShift() {
        BigNumberImpl bigNumber = new BigNumberImpl("3");
        bigNumber.shiftRight(-9);
        System.out.println(bigNumber.toString());

    }
}