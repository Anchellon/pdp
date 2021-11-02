package maze.impl;

import maze.enums.MazeTypeEnum;

/**
 * @author novo
 * @since 2021/10/30
 */
public class WrappingRoomMaze extends RoomMaze{
    public WrappingRoomMaze(int numOfRows, int numOfColumns, int numOfWalls) {
        super(MazeTypeEnum.WRAPPING_ROOM, numOfRows, numOfColumns, numOfWalls);
    }
}
