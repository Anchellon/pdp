import maze.Cell;
import maze.impl.GoldCell;
import maze.impl.ThiefCell;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.assertEquals;

/**
 * @author novo
 * @since 2021/11/2
 */
public class CellTest {
    Player player;

    @Before
    public void init() {
        player = new Player("zhang san");
        player.setLocation(0, 0);
    }

    /**
     * correctly use
     */
    @Test
    public void testProcessPlayer() {
        Cell goldCell = new GoldCell(10, 10, 100);
        goldCell.processPlayer(player);
        assertEquals(100, player.getGoldCount());

        Cell thiefCell = new ThiefCell(11, 10);
        thiefCell.processPlayer(player);
        assertEquals(90, player.getGoldCount());
    }


    /**
     * illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructGoldCell() {
        new GoldCell(-1, -1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructThiefCell() {
        new ThiefCell(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructGoldCellWithNegativeGold() {
        new GoldCell(0, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdjacent() {
        Cell cell = new GoldCell(0, 0, 100);
        cell.isAdjacent(null, 100, 100, false);
    }

}
