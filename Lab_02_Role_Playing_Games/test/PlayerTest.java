import item.Gear;
import item.impl.Footwear;
import item.impl.HandWear;
import item.impl.HeadWear;
import org.junit.Before;
import org.junit.Test;
import player.Player;

/**
 * @author novo
 * @since 2021/10/18
 */
public class PlayerTest {

    Player player1;
    Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("zhangsan", 10, 25);
        player2 = new Player("lisi");
    }

    @Test
    public void pickup() throws Exception {
        Gear greenHat = new HeadWear("Green", "Hat", 1);
        Gear redGlove = new HandWear("Red", "Glove", 100);
        Gear blueBoot = new Footwear("Blue", "Boot", 77, 50);

        player1.pickup(greenHat);
        player1.pickup(redGlove);
        player1.pickup(blueBoot);

        System.out.println(player1.toString());
    }

    /**
     * Try to create player with null argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerWithNullArgument() {
        Player player = new Player(null);
    }

    /**
     * Try to create player with empty string
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerWithEmptyString() {
        Player player = new Player("");
    }

    /**
     * Try to pickup null gear
     */
    @Test(expected = IllegalArgumentException.class)
    public void pickupNullGear() throws Exception {
        player1.pickup(null);
    }

    /**
     * try to pickup excessive head gears
     */
    @Test(expected = IllegalStateException.class)
    public void tryToAddExcessiveHeadGears() throws Exception {
        Gear waterBubble = new HeadWear("Water", "Bubble", 34);
        Gear yellowHair = new HeadWear("Yellow", "Hair", 26);
        Gear greyHelmet = new HeadWear("Grey", "Helmet", 16);
        player1.pickup(waterBubble);
        player1.pickup(yellowHair);
        player1.pickup(greyHelmet);
    }

    /**
     * try to pickup excessive hand gears
     */
    @Test(expected = IllegalStateException.class)
    public void tryToAddExcessiveHandGears() throws Exception {
        Gear awesomeOreo = new HandWear("Awesome", "Oreo", 27);
        Gear bigIpad = new HandWear("Big", "Ipad", 88);
        Gear pinkKindle = new HandWear("Pink", "Kindle", 12);
        Gear blackKeyboard = new HandWear("Black", "Keyboard", 55);
        Gear redGlove = new HandWear("Red", "Glove", 100);
        player1.pickup(awesomeOreo);
        player1.pickup(bigIpad);
        player1.pickup(pinkKindle);
        player1.pickup(blackKeyboard);
        player1.pickup(redGlove);
    }

    /**
     * try to pickup excessive foot wears
     */
    @Test(expected = IllegalStateException.class)
    public void tryToAddExcessiveFootWears() throws Exception {
        Gear burningPuma = new Footwear("Burning", "Puma", 60, 17);
        Gear wetSock = new Footwear("Wet", "Sock", 12, 80);
        Gear ironNike = new Footwear("Iron", "Nike", 99, 99);
        Gear smallBox = new Footwear("Small", "Box", 21, 33);
        Gear blueBoot = new Footwear("Blue", "Boot", 77, 50);
        player1.pickup(burningPuma);
        player1.pickup(wetSock);
        player1.pickup(ironNike);
        player1.pickup(smallBox);
        player1.pickup(blueBoot);
    }



    /**
     * test toString() when player doesn't have gears
     */
    @Test
    public void testToStringWithoutGears() {
        Player player = new Player("test");
        System.out.println(player.toString());
    }


}