package maze;

import maze.enums.CellTypeEnum;
import player.Player;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/25
 */
public interface Cell {

    Location getLocation();

    void setLocation(Location location);

    String processPlayer(Player player, Maze maze);

    boolean isAdjacent(Cell cell, int numOfRows, int numOfColumns, boolean isWrapping);

    Set<CellTypeEnum> getType();

    void addType(CellTypeEnum type);

    long getId();
}
