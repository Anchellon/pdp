package maze;

import maze.enums.CellTypeEnum;
import player.Player;
import util.CountUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/25
 */
public class AbstractCell implements Cell {
    private Location location;
    private Set<CellTypeEnum> type;
    private final long cellId;

    /**
     * constructors
     */
    public AbstractCell(Location location) {
        if (location.getX() < 0 || location.getY() < 0) {
            throw new IllegalArgumentException("argument error");
        }
        this.type = new HashSet<>();
        this.location = location;
        // generate id
        this.cellId = CountUtil.createCell();
    }

    public AbstractCell(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("argument error");
        }
        this.type = new HashSet<>();
        this.location = new Location(x, y);
        // generate id
        this.cellId = CountUtil.createCell();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * in abstract cell, it will only process the location
     *
     * @param player target player
     */
    @Override
    public void processPlayer(Player player, Maze maze) {
        if (player == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        // change location
        player.setLocation(this.getLocation());
        System.out.println("【info】 Move to (" + player.getLocation().getX() + ", " + player.getLocation().getY() + ")");
    }

    /**
     * Check other cell is adjacent to this cell or not.
     * Using distance of these two cells.
     *
     * @param cell         target cell
     * @param numOfRows    number of rows in this maze
     * @param numOfColumns number of columns in this maze
     * @param isWrapping   this maze is wrapping or not
     * @return target cell is adjacent or not
     */
    @Override
    public boolean isAdjacent(Cell cell, int numOfRows, int numOfColumns, boolean isWrapping) {
        if (cell == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        if (numOfColumns <= 0 || numOfRows <= 0) {
            throw new IllegalArgumentException("maze size error");
        }
        // no matter the maze is wrapping or not, if these two cell's manhattan distance is
        // smaller than 2, then they are adjacent
        int distance = this.location.getManhattanDistance(cell.getLocation());
        if (distance == 1) {
            return true;
        }

        // 0 1 2

        // if distance > 1, we then need to check if it's a wrapping maze
        if (isWrapping) {
            if (cell.getLocation().getX() == this.location.getX() && distance == numOfColumns - 1) {
                // in the same row, check if they are the first and the last one in this row
                return true;
            } else if (cell.getLocation().getY() == this.location.getY() && distance == numOfRows - 1) {
                // in the same column
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<CellTypeEnum> getType() {
        return type;
    }

    @Override
    public void addType(CellTypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("argument error");
        }
        this.type.add(type);
    }

    @Override
    public long getId() {
        return cellId;
    }
}
