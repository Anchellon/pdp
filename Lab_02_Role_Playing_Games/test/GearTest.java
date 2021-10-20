import gear.Gear;
import gear.impl.Footwear;
import gear.impl.HandWear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author novo
 * @since 2021/10/19
 */
public class GearTest {
    Gear awesomeOreo;
    Gear bigIpad;
    Gear pinkKindle;
    Gear blackKeyboard;
    Gear redGlove;

    @Before
    public void setUp() {
        awesomeOreo = new HandWear("Awesome", "Oreo", 27);
        bigIpad = new HandWear("Big", "Ipad", 88);
        pinkKindle = new HandWear("Pink", "Kindle", 12);
        blackKeyboard = new HandWear("Black", "Keyboard", 55);
        redGlove = new HandWear("Red", "Glove", 100);
    }

    /**
     * Test combine gears correctly
     */
    @Test
    public void testCombine() throws Exception {
        awesomeOreo.combine(bigIpad);
        long id = awesomeOreo.getId();
        assertEquals("AbstractGear{gearId=" + id + ", type=HAND, adj='Awesome, Big', noun='Ipad', attack=115, defense=0, hasCombined=true}"
                , awesomeOreo.toString());
    }

    /**
     * Test combining different types of gears
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCombineDifferentTypesOfGears() throws Exception {
        Gear fireAdidas = new Footwear("Fire", "Adidas", 80, 12);
        awesomeOreo.combine(fireAdidas);
    }

    /**
     * Create gears with null or empty string
     */
    @Test(expected = IllegalArgumentException.class)
    public void createGearWithNullOrEmpty() {
        Gear test = new Footwear(null, "", 1, 2);
    }

    /**
     * Try to combine excessive gears
     */
    @Test(expected = IllegalStateException.class)
    public void tryToCombineThreeGears() throws Exception {
        awesomeOreo
                .combine(bigIpad)
                .combine(bigIpad);
    }

    /**
     * Try to combine with null gear
     */
    @Test(expected = IllegalArgumentException.class)
    public void tryToCombineNullGear() throws Exception {
        awesomeOreo.combine(null);
    }
}
