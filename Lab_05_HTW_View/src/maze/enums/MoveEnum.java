package maze.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author novo
 * @since 2021/10/25
 */
public enum MoveEnum {
    MOVE_NORTH(-1, 0, 'n'),
    MOVE_WEST(0, -1, 'w'),
    MOVE_SOUTH(1, 0, 's'),
    MOVE_EAST(0, 1, 'e');

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
        if (c != 'n' && c != 'w' && c != 's' && c != 'e') {
            throw new IllegalArgumentException("illegal key, should be one of [n, w, s, e]");
        }
        switch (c) {
            case 'n':
                return MOVE_NORTH;
            case 'w':
                return MOVE_WEST;
            case 's':
                return MOVE_SOUTH;
            case 'e':
                return MOVE_EAST;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return getKey() + "";
    }
}
