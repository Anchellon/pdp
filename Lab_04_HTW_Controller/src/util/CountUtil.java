package util;

/**
 * @author novo
 * @since 2021/10/25
 */
public class CountUtil {
    private static long mazeCount = 0;
    private static long playerCount = 0;
    private static long cellCount = 0;

    public static long createMaze() {
        return ++mazeCount;
    }

    public static long createPlayer() {
        return ++playerCount;
    }

    public static long createCell() {
        return ++cellCount;
    }

    public static long getNumberOfMaze() {
        return mazeCount;
    }

    public static long getNumberOfPlayer() {
        return playerCount;
    }

    public static long getCellCount() {
        return cellCount;
    }

}
