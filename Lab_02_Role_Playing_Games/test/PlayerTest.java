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

    @Test
    public void testToString() {

    }
}