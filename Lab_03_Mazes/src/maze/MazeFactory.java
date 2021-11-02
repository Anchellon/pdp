package maze;

import maze.enums.MazeTypeEnum;
import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.RoomMaze;
import maze.impl.WrappingRoomMaze;

/**
 * @author novo
 * @since 2021/10/25
 */
public class MazeFactory {
    public static Maze generatePerfectMaze(int numOfRows, int numOfColumns) {
        return new PerfectMaze(numOfRows, numOfColumns);
    }

    public static Maze generateRoomMaze(int numOfRows, int numOfColumns, int numOfWalls, boolean isWrapping) {
        if (isWrapping) {
            return new WrappingRoomMaze(numOfRows, numOfColumns, numOfWalls);
        } else {
            return new NonWrappingRoomMaze(numOfRows, numOfColumns, numOfWalls);
        }
    }
}
