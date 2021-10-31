package maze;

import player.Player;

/**
 * @author novo
 * @since 2021/10/25
 */
public interface Cell {
    boolean isThiefCell();

    boolean isGoldCell();

    Location getLocation();

    void setLocation(Location location);

    void processPlayer(Player player);

    boolean isAdjacent(Cell cell, int numOfRows, int numOfColumns, boolean isWrapping);
}
