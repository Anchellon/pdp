package maze;

import maze.enums.CellTypeEnum;
import player.Player;

/**
 * @author novo
 * @since 2021/10/25
 */
public class AbstractCell implements Cell {
    private Location location;
    private CellTypeEnum type;

    public AbstractCell(CellTypeEnum type, Location location) {
        if (type == null || location == null || location.getX() < 0 || location.getY() < 0) {
            throw new IllegalArgumentException("argument error");
        }
        this.type = type;
        this.location = location;
    }

    public AbstractCell(CellTypeEnum type, int x, int y) {
        if (type == null || x < 0 || y < 0) {
            throw new IllegalArgumentException("argument error");
        }
        this.type = type;
        this.location = new Location(x, y);
    }

    @Override
    public boolean isThiefCell() {
        return type == CellTypeEnum.THIEF;
    }

    @Override
    public boolean isGoldCell() {
        return type == CellTypeEnum.GOLD;
    }

    @Override
    public long getGold() {
        if (!isGoldCell()) {
            throw new IllegalStateException("it's not a gold cell");
        }
        return 0;
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
    public void processPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        // change location
        player.setLocation(this.getLocation());
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
        if (distance <= 1) {
            return true;
        }

        // if distance > 1, we then need to check if it's a wrapping maze
        if (isWrapping) {
            if (cell.getLocation().getX() == this.location.getX() && distance == numOfColumns) {
                // in the same row, check if they are the first and the last one in this row
                return true;
            } else if (cell.getLocation().getY() == this.location.getY() && distance == numOfRows) {
                // in the same column
                return true;
            }
        }
        return false;
    }
}
