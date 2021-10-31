package maze.impl;

import maze.AbstractCell;
import maze.Location;
import maze.enums.CellTypeEnum;
import player.Player;

/**
 * @author novo
 * @since 2021/10/25
 */
public class GoldCell extends AbstractCell {

    private long gold;

    /**
     * Constructor
     */
    public GoldCell(Location location, long gold) {
        super(CellTypeEnum.GOLD, location);
        this.gold = gold;
    }

    /**
     * Constructor
     */
    public GoldCell(int x, int y, long gold) {
        super(CellTypeEnum.GOLD, x, y);
        this.gold = gold;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public void clearGold() {
        this.gold = 0;
    }

    @Override
    public void processPlayer(Player player) {
        // use parent class to process location
        super.processPlayer(player);
        // process gold count
        player.pickGold(gold);
        // clear gold in this cell
        clearGold();
    }
}
