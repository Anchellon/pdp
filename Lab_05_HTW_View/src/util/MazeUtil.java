package util;

/**
 * @author novo
 * @since 2021/12/9
 */
public class MazeUtil {
    /**
     * check setting is valid or not
     */
    public static boolean isValidMaze(int rows, int cols, int walls, int pits, int bats) {
        if (rows <= 0 || cols <= 0 || walls < 0 || pits < 0 || bats < 0) {
            return false;
        }
        int maxNumOfWalls = cols * (rows - 1) + rows * (cols - 1) - cols * rows + 1;
        if (walls > maxNumOfWalls) {
            return false;
        }
        if (pits > rows * cols || bats > rows * cols) {
            return false;
        }
        return true;
    }
}
