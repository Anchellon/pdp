import org.junit.Before;
import org.junit.Test;
import player.Player;

/**
 * @author novo
 * @since 2021/11/2
 */
public class PlayerTest {
    Player player;

    @Before
    public void init() {
        player = new Player("zhangsan");
        player.setLocation(0, 0);
        player.pickGold(100);
    }

    /**
     * illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithNull() {
        Player player2 = new Player(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullLocation() {
        player.setLocation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMove() {
        player.move(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeGold() {
        player.setGoldCount(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPickNegativeGold() {
        player.pickGold(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoseNegativeGold() {
        player.loseGold(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalLosePercentage() {
        player.loseGoldByPercentage(1000);
    }


    /**
     * Illegal state exception
     */
    @Test(expected = IllegalStateException.class)
    public void testLoseExcessiveGold() {
        player.loseGold(100000);
    }

}
