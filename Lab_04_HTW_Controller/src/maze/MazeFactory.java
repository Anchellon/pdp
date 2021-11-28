package maze;

import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.WrappingRoomMaze;

/**
 * @author novo
 * @since 2021/10/25
 */
public class MazeFactory {
    public static Maze generatePerfectMaze(int numOfRows, int numOfColumns,
                                           int numOfBat,
                                           int numOfPit) {
        return new PerfectMaze(numOfRows, numOfColumns, numOfBat, numOfPit);
    }

    public static Maze generateRoomMaze(int numOfRows, int numOfColumns, int numOfWalls, boolean isWrapping,
                                        int numOfBat,
                                        int numOfPit) {
        if (isWrapping) {
            return new WrappingRoomMaze(numOfRows, numOfColumns, numOfWalls, numOfBat, numOfPit);
        } else {
            return new NonWrappingRoomMaze(numOfRows, numOfColumns, numOfWalls, numOfBat, numOfPit);
        }
    }
}
