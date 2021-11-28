package maze.impl;

import maze.enums.MazeTypeEnum;

/**
 * @author novo
 * @since 2021/10/30
 */
public class WrappingRoomMaze extends RoomMaze{
    public WrappingRoomMaze(int numOfRows, int numOfColumns, int numOfWalls,
                            int numOfBat,
                            int numOfPit) {
        super(MazeTypeEnum.WRAPPING, numOfRows, numOfColumns, numOfWalls, numOfBat, numOfPit);
    }
}
