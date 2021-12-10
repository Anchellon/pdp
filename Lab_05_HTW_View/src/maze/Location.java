package maze;

import maze.enums.MoveEnum;

/**
 * location of items
 *
 * @author novo
 * @since 2021/10/25
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(MoveEnum moveEnum) {
        if (moveEnum == null) {
            throw new IllegalArgumentException("Move enum cannot be null");
        }
        this.x += moveEnum.getX();
        this.y += moveEnum.getY();
    }

    /**
     * Get manhattan distance to another location
     * <p>
     * |x1 - x2| + |y1 - y2|
     *
     * @param location target location
     * @return manhattan distance
     */
    public int getManhattanDistance(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        return Math.abs(this.getX() - location.getX()) + Math.abs(this.getY() - location.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            return this.getX() == ((Location)obj).getX() && this.getY() == ((Location)obj).getY();
        }
        return false;
    }

    /**
     * rewrite hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return this.getX() * 10000 + this.getY();
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
