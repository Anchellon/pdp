import maze.AbstractMaze;
import maze.Maze;
import maze.MazeFactory;
import maze.enums.MoveEnum;
import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.WrappingRoomMaze;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author novo
 * @since 2021/10/31
 */
public class MazeTest {

    Maze generalMaze;

    @Before
    public void init() {
        generalMaze = MazeFactory.generatePerfectMaze(5, 5);
    }

    @Test
    public void testInitAndPrint() {
//        Maze maze = MazeFactory.generatePerfectMaze(3, 4);
        AbstractMaze maze = new PerfectMaze(10, 20);
//        AbstractMaze maze = new WrappingRoomMaze(3, 4, 5);
        maze.setStartLocation(0, 0);
        maze.setGoalLocation(2, 3);
        Player player = new Player("zhangsan");

        maze.setPlayer(player);

        maze.printMaze();
    }


    /**
     * Illegal Argument Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithNull() {
        Maze maze = new PerfectMaze(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullPlayer() {
        generalMaze.setPlayer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateExcessiveWalls() {
        Maze roomMaze = MazeFactory.generateRoomMaze(5, 5, 100, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void movePlayerWithNull() {
        generalMaze.setStartLocation(0, 0);
        generalMaze.setGoalLocation(1, 1);
        Player player = new Player("zs");
        generalMaze.setPlayer(player);
        generalMaze.movePlayer(null);
    }

    /**
     * Illegal State Exception
     */
    @Test(expected = IllegalStateException.class)
    public void movePlayerWhileNoPlayerExists() {
        generalMaze.movePlayer(MoveEnum.MOVE_NORTH);
    }

    @Test(expected = IllegalStateException.class)
    public void movePlayerWhileNoLocationSet() {
        Player player = new Player("zs");
        generalMaze.setPlayer(player);
    }

    /**
     * Out Of Bounds Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void stepOutOfBounds() {
        generalMaze.setStartLocation(0, 0);
        generalMaze.setGoalLocation(1, 1);
        Player player = new Player("zs");
        generalMaze.setPlayer(player);
        generalMaze.movePlayer(MoveEnum.MOVE_WEST);
    }



    //    @Test
    public static void letsPlay() throws IOException {
        Scanner scanner = new Scanner(System.in);
        AbstractMaze maze = null;
        while (maze == null) {
            // maze
            int numOfRows, numOfColumns;
            System.out.println("Create Maze! Please type in number of rows: ");
            numOfRows = scanner.nextInt();
            System.out.println("Please type in number of columns: ");
            numOfColumns = scanner.nextInt();
            while (maze == null) {
                System.out.println("Is this a perfect maze? (y/n)");
                String isPerfectMaze = scanner.next();
                if (isPerfectMaze.equals("y")) {
                    maze = new PerfectMaze(numOfRows, numOfColumns);
                } else if (isPerfectMaze.equals("n")) {
                    int maxWalls = numOfRows * (numOfColumns - 1) + numOfColumns * (numOfRows - 1) - numOfColumns * numOfRows + 1;
                    int numOfWalls;
                    while (true) {
                        System.out.println("How many walls are there in this maze?");
                        numOfWalls = scanner.nextInt();
                        if (numOfWalls < 0 || numOfWalls >= maxWalls) {
                            System.out.println("INVALID NUMBER OF WALLS");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("Is this a wrapping maze? (y/n)");
                        String isWrapping = scanner.next();
                        if (isWrapping.equals("y")) {
                            maze = new WrappingRoomMaze(numOfRows, numOfColumns, numOfWalls);
                            break;
                        } else if (isWrapping.equals("n")) {
                            maze = new NonWrappingRoomMaze(numOfRows, numOfColumns, numOfWalls);
                            break;
                        } else {
                            System.out.println("INVALID CODE!");
                        }
                    }
                } else {
                    System.out.println("INVALID CODE!");
                }
            }
            Runtime.getRuntime().exec("clear");

            // start and goal locations
            int startX = -1, startY = -1;
            int goalX = -1, goalY = -1;
            while (startX == -1 || startY == -1 || goalX == -1 || goalY == -1) {
                System.out.println("Do you want to auto generate start and goal locations? (y/n)");
                String autoGenerate = scanner.next();
                if (autoGenerate.equals("y")) {
                    maze.randomStartAndGoal();
                    break;
                } else if (autoGenerate.equals("n")) {
                    while (true) {
                        // start
                        System.out.println("Please type in start position in [x,y] format: ");
                        String startPosition = scanner.next();
                        String[] startCoordinates = startPosition.split(",");
                        if (startPosition.equals("") || startCoordinates.length != 2) {
                            System.out.println("INVALID CODE");
                            continue;
                        } else {
                            startX = Integer.parseInt(startCoordinates[0]);
                            startY = Integer.parseInt(startCoordinates[1]);
                        }
                        // goal
                        System.out.println("Please type in goal position in [x,y] format: ");
                        String goalPosition = scanner.next();
                        String[] goalCoordinates = goalPosition.split(",");
                        if (goalPosition.equals("") || goalCoordinates.length != 2) {
                            System.out.println("INVALID CODE");
                            continue;
                        } else {
                            goalX = Integer.parseInt(goalCoordinates[0]);
                            goalY = Integer.parseInt(goalCoordinates[1]);
                        }
                        maze.setStartLocation(startX, startY);
                        maze.setGoalLocation(goalX, goalY);
                        break;
                    }
                } else {
                    System.out.println("INVALID CODE");
                }
            }
            Runtime.getRuntime().exec("clear");

            // player
            Player player = null;
            while (player == null) {
                System.out.println("Please type in your name:");
                String playerName = scanner.next();
                player = new Player(playerName);
            }
            maze.setPlayer(player);
            Runtime.getRuntime().exec("clear");
        }

        // start playing
        while (true) {
            try {
//            maze.printMaze();
//            System.out.println();
                maze.printPossibleDirections();
                System.out.println("Please type in your next move(w, a, s, d): ");
                String typein = scanner.next();
                if (typein.equals("")) {
                    System.out.println("INVALID CODE");
                }
                char code = typein.charAt(0);
                if (code == 'w' || code == 'a' || code == 's' || code == 'd') {
                    maze.movePlayer(code);
                    Runtime.getRuntime().exec("clear");
                } else if (code == 'm') {
                    // print maze
                    maze.printMaze();
                } else if (code == 'p') {
                    maze.printPlayerInfo();
                } else {
                    System.out.println("INVALID CODE");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (e.getClass() == RuntimeException.class) {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        letsPlay();
    }
}
