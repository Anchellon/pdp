package player;

import maze.Location;
import maze.enums.MoveEnum;
import util.CountUtil;

/**
 * @author novo
 * @since 2021/10/25
 */
public class Player {
    private final long playerId;
    private Location location;
    private int numOfArrow;

    /**
     * Constructor of player
     */
    public Player() {
        this.playerId = CountUtil.createPlayer();
    }

    /**
     * Getters and setters
     */
    public long getPlayerId() {
        return playerId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        this.location = location;
    }

    public void setLocation(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("argument cannot be negative");
        }
        this.location = new Location(x, y);
    }

    /**
     * move player in specific direction
     * @param moveEnum moveEnum
     */
    public void move(MoveEnum moveEnum) {
        if (moveEnum == null) {
            throw new IllegalArgumentException("Move Enum cannot be null");
        }
        this.location.move(moveEnum);
    }

    public int getNumOfArrow() {
        return numOfArrow;
    }

    public void setNumOfArrow(int numOfArrow) {
        this.numOfArrow = numOfArrow;
    }
}
