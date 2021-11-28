package maze.impl;

import maze.AbstractMaze;
import maze.enums.MazeTypeEnum;

/**
 * @author novo
 * @since 2021/10/24
 */
public class PerfectMaze extends AbstractMaze {
    public PerfectMaze(int numOfRows,
                       int numOfColumns,
                       int numOfBat,
                       int numOfPit) {
        // num of walls = rows * (cols - 1) + cols * (rows - 1)
        super(MazeTypeEnum.PERFECT,
                numOfRows,
                numOfColumns,
                numOfRows * (numOfColumns - 1) + numOfColumns * (numOfRows - 1) - numOfRows * numOfColumns + 1,
                numOfBat,
                numOfPit);
    }
}
