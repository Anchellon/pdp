package maze.enums;

/**
 * @author novo
 * @since 2021/10/25
 */
public enum MoveEnum {
    MOVE_NORTH(-1, 0),
    MOVE_SOUTH(1, 0),
    MOVE_EAST(0, 1),
    MOVE_WEST(-1, 0);

    int x;
    int y;

    MoveEnum(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
