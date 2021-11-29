import maze.Maze;
import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.WrappingRoomMaze;

import java.util.Scanner;

/**
 * @author novo
 * @since 2021/11/25
 */
public class HTWDriver {
    Scanner scanner = new Scanner(System.in);
    Maze maze;

    /**
     * initializing maze and player
     */
    private void init() {
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
        System.out.println("number of columns (1 - 5)?");
        int numOfColumns = scanner.nextInt();
        System.out.println("number of bats (1 - 5)?");
        int numOfBats = scanner.nextInt();
        System.out.println("number of pits (1 - 5)?");
        int numOfPits = scanner.nextInt();
        System.out.println("number of arrows (1 - 5)?");
        int numOfArrows = scanner.nextInt();
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
    }

    public Maze getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        HTWDriver driver = new HTWDriver();
        driver.init();
        Maze maze = driver.getMaze();
        Scanner scanner = new Scanner(System.in);
        maze.printStartLocationChoices();
        maze.setStartLocationByRoomId(scanner.nextLong());
        while (!maze.isEnd()) {
            // print possible moves
            maze.printPossibleDirections();
            System.out.println("Shoot or Move (s-m)?");
            String op = scanner.next();
            switch (op) {
                case "s":
                    System.out.println("Distance (1-5)?");
                    int distance = scanner.nextInt();
                    System.out.println("Toward (n, s, w, e)?");
                    String direction = scanner.next();
                    maze.shoot(direction.charAt(0), distance);
                    break;
                case "m":
                    System.out.println("Toward (n, s, w, e)?");
                    // get input
                    String typein = scanner.next();
                    if (typein.equals("")) {
                        System.out.println("INVALID CODE");
                    }
                    char code = typein.charAt(0);
                    if (code == 'n' || code == 's' || code == 'w' || code == 'e') {
                        maze.movePlayer(code);
                    } else {
                        System.out.println("INVALID CODE");
                    }
                    break;
                case "o":
                    // print maze
                    maze.printMaze();
                    break;
                case "p":
                    maze.printPlayerInfo();
                    break;
                default:
                    System.out.println("INVALID CODE");
                    break;
            }
        }
    }
}
