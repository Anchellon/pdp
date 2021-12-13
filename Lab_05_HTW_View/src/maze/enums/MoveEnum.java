package maze.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author novo
 * @since 2021/10/25
 */
public enum MoveEnum {
    MOVE_NORTH(-1, 0, 'w'),
    MOVE_WEST(0, -1, 'a'),
    MOVE_SOUTH(1, 0, 's'),
    MOVE_EAST(0, 1, 'd');

    int x;
    int y;
    char key;

    MoveEnum(int x, int y, char key) {
        this.x = x;
        this.y = y;
        this.key = key;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getKey() {
        return key;
    }

    // return a list of all move enums
    private static final List<MoveEnum> allDirections = new ArrayList<>(Arrays.asList(MOVE_NORTH, MOVE_EAST, MOVE_SOUTH, MOVE_WEST));
    public static List<MoveEnum> getAllDirections() {
        return allDirections;
    }

    public static MoveEnum convertKey(char c) {
        if (c != 'w' && c != 'a' && c != 's' && c != 'd') {
            throw new IllegalArgumentException("illegal key, should be one of [w, a, s, d]");
        }
        switch (c) {
            case 'w':
                return MOVE_NORTH;
            case 'a':
                return MOVE_WEST;
            case 's':
                return MOVE_SOUTH;
            case 'd':
                return MOVE_EAST;
            default:
                return null;
        }
    }

    public static MoveEnum parse(int x, int y) {
        if (x == MOVE_EAST.getX() && y == MOVE_EAST.getY()) {
            return MOVE_EAST;
        }
        if (x == MOVE_WEST.getX() && y == MOVE_WEST.getY()) {
            return MOVE_WEST;
        }
        if (x == MOVE_SOUTH.getX() && y == MOVE_SOUTH.getY()) {
            return MOVE_SOUTH;
        }
        if (x == MOVE_NORTH.getX() && y == MOVE_NORTH.getY()) {
            return MOVE_NORTH;
        }
        return null;
    }

    @Override
    public String toString() {
        return getKey() + "";
    }
}
