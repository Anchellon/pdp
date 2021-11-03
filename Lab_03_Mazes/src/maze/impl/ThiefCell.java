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

    // each thief cell will only be used once
    private boolean active;

    /**
     * Constructor
     */
    public ThiefCell(Location location) {
        super(CellTypeEnum.THIEF, location);
        this.active = true;
    }

    /**
     * Constructor
     */
    public ThiefCell(int x, int y) {
        super(CellTypeEnum.THIEF, x, y);
        this.active = true;
    }

    @Override
    public void processPlayer(Player player) {
        // process location by parent
        super.processPlayer(player);
        if (active) {
            // lose default ratio of gold
            player.loseGoldByPercentage(DEFAULT_STEAL_RATIO);
            System.out.println("【info】 Encountered thief, lost 10% of gold");
            // set this cell to inactive
            active = false;
        }
    }
}
