package view;

import maze.Maze;
import maze.impl.NonWrappingRoomMaze;

import javax.swing.*;

/**
 * @author novo
 * @since 2021/12/9
 */
public class GameFrame extends JPanel {
    private Maze maze;
    // game setting
    private int numOfRows;
    private int numOfCols;
    private int numOfWalls;
    private int numOfBats;
    private int numOfPits;
    private int numOfArrows;

    public void init(int numOfRows,
                     int numOfCols,
                     int numOfWalls,
                     int numOfBats,
                     int numOfPits,
                     int numOfArrows) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
        this.numOfWalls = numOfWalls;
        this.numOfBats = numOfBats;
        this.numOfPits = numOfPits;
        this.numOfArrows = numOfArrows;

        // create maze
        Maze maze = new NonWrappingRoomMaze(numOfRows, numOfCols, numOfWalls, numOfBats, numOfPits);
        maze.printMaze();
    }

}
