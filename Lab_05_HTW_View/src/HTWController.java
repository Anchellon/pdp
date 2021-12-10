import maze.Maze;

import java.util.Scanner;

/**
 * @author novo
 * @since 2021/11/28
 */
public class HTWController {
    Maze maze;
    Scanner scanner;

    public HTWController(Maze maze) {
        this.maze = maze;
        scanner = new Scanner(System.in);
    }

    public void run() {
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
            case "x":
                if (maze.isWinnable()) {
                    System.out.println("Winnable");
                } else {
                    System.out.println("Unwinnable");
                }
                break;
            default:
                System.out.println("INVALID CODE");
                break;
        }
    }
}
