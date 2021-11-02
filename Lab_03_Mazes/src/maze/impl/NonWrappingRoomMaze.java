package maze.impl;

import maze.enums.MazeTypeEnum;

/**
 * @author novo
 * @since 2021/10/31
 */
public class NonWrappingRoomMaze extends RoomMaze{

    public NonWrappingRoomMaze(int numOfRows, int numOfColumns, int numOfWalls) {
        super(MazeTypeEnum.NON_WRAPPING_ROOM, numOfRows, numOfColumns, numOfWalls);
    }
}
