package driver;

import maze.AbstractMaze;
import maze.Maze;
import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.WrappingRoomMaze;

import java.util.Scanner;

/**
 * @author novo
 * @since 2021/11/25
 */
public class HTWTextDriver {

    Scanner scanner = new Scanner(System.in);
    Maze maze;
    HTWController controller;

    /**
     * initializing maze and player
     */
    private void init() {
        // set up maze properties
        System.out.println("Is it a perfect maze (y/n)?");
        String input = scanner.next();
        boolean isPerfect;
        if (input.equals("y") || input.equals("n")) {
            isPerfect = input.equals("y");
        } else {
            throw new IllegalArgumentException("INVALID CODE");
        }
        boolean isWrapping = false;
        int numOfWalls = 0;
        if (!isPerfect) {
            // if it's not perfect
            System.out.println("type of maze (0: non-wrapping, 1:wrapping): ");
            int inputInt = scanner.nextInt();
            if (inputInt != 0 && inputInt != 1) {
                throw new IllegalArgumentException("INVALID CODE");
            }
            isWrapping = inputInt == 1;
            System.out.println("number of walls: ");
            inputInt = scanner.nextInt();
            if (inputInt < 0) {
                throw new IllegalArgumentException("INVALID CODE");
            }
            numOfWalls = inputInt;
        }
        System.out.println("number of rows (1 - 5)?");
        int numOfRows = scanner.nextInt();
        if (numOfRows < 1 || numOfRows > 5) {
            throw new IllegalArgumentException("illegal number of rows");
        }
        System.out.println("number of columns (1 - 5)?");
        int numOfColumns = scanner.nextInt();
        if (numOfColumns < 1 || numOfColumns > 5) {
            throw new IllegalArgumentException("illegal number of columns");
        }
        System.out.println("number of bats (1 - 5)?");
        int numOfBats = scanner.nextInt();
        if (numOfBats < 1 || numOfBats > 5) {
            throw new IllegalArgumentException("illegal number of bats");
        }
        System.out.println("number of pits (1 - 5)?");
        int numOfPits = scanner.nextInt();
        if (numOfPits < 1 || numOfPits > 5) {
            throw new IllegalArgumentException("illegal number of pits");
        }
        System.out.println("number of arrows (1 - 5)?");
        int numOfArrows = scanner.nextInt();
        if (numOfArrows < 1 || numOfArrows > 5) {
            throw new IllegalArgumentException("illegal number of arrows");
        }
        if (isPerfect) {
            maze = new PerfectMaze(numOfRows, numOfColumns, numOfBats, numOfPits);
        } else {
            if (isWrapping) {
                maze = new WrappingRoomMaze(numOfRows, numOfColumns, numOfWalls, numOfBats, numOfPits);
            } else {
                maze = new NonWrappingRoomMaze(numOfRows, numOfColumns, numOfWalls, numOfBats, numOfPits);
            }
        }
        maze.setNumOfArrows(numOfArrows);
        // initialize controller
        controller = new HTWController(maze);
        // set up start location
        maze.printStartLocationChoices();
        maze.setStartLocationByRoomId(scanner.nextLong());
    }

    /**
     * start game!
     */
    public void startGame() {
        while (maze.getGameStatus() == AbstractMaze.ALIVE) {
            // if it's not end
            controller.run();
        }
    }

    public static void main(String[] args) {
        HTWTextDriver driver = new HTWTextDriver();
        driver.init();
        driver.startGame();
    }
}
