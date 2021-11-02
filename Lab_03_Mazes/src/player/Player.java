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
    private final String name;
    private Location location;
    private long goldCount;

    /**
     * Constructor of player
     */
    public Player(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("argument is null");
        }
        this.name = name;
        this.playerId = CountUtil.createPlayer();
        goldCount = 0;
    }

    /**
     * Getters and setters
     */
    public long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
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

    public long getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(long goldCount) {
        if (goldCount < 0) {
            throw new IllegalArgumentException("negative argument");
        }
        this.goldCount = goldCount;
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

    /**
     * pick gold
     */
    public void pickGold(long gold) {
        if (gold <= 0) {
            throw new IllegalArgumentException("argument should be positive");
        }
        this.goldCount += gold;
    }

    /**
     * lose gold
     */
    public void loseGold(long gold) {
        if (gold <= 0) {
            throw new IllegalArgumentException("argument should be positive");
        }
        if (gold > this.goldCount) {
            throw new IllegalStateException("argument should be less than goldCount");
        }
        this.goldCount -= gold;
    }

    /**
     * lose gold by percentage
     * @param percentage 0 <= percentage <= 100
     */
    public void loseGoldByPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("percentage should be [0, 100]");
        }
        this.goldCount *= (100 - percentage) / 100.0;
    }
}
