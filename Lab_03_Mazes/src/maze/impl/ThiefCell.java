package maze.impl;

import maze.AbstractCell;
import maze.Location;
import maze.enums.CellTypeEnum;
import player.Player;

/**
 * @author novo
 * @since 2021/10/25
 */
public class ThiefCell extends AbstractCell {

    private static final int DEFAULT_STEAL_RATIO = 10;

    /**
     * Constructor
     */
    public ThiefCell(Location location) {
        super(CellTypeEnum.THIEF, location);
    }

    /**
     * Constructor
     */
    public ThiefCell(int x, int y) {
        super(CellTypeEnum.THIEF, x, y);
    }

    @Override
    public void processPlayer(Player player) {
        // process location by parent
        super.processPlayer(player);
        // lose default ratio of gold
        player.loseGoldByPercentage(DEFAULT_STEAL_RATIO);
    }
}