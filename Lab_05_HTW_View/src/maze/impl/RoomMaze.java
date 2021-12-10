package maze.impl;

import maze.AbstractMaze;
import maze.enums.MazeTypeEnum;

/**
 * @author novo
 * @since 2021/10/24
 */
public abstract class RoomMaze extends AbstractMaze {
    public RoomMaze(MazeTypeEnum type, int numOfRows, int numOfColumns, int numOfWalls,
                    int numOfBat,
                    int numOfPit) {
        super(type, numOfRows, numOfColumns, numOfWalls, numOfBat, numOfPit);
        if (type != MazeTypeEnum.NON_WRAPPING && type != MazeTypeEnum.WRAPPING) {
            throw new IllegalArgumentException("Illegal maze type to create room maze");
        }
    }
}
