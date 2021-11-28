import maze.impl.PerfectMaze;

import java.util.Scanner;

/**
 * @author novo
 * @since 2021/11/25
 */
public class HTWDriver {
    public static void main(String[] args) {
        PerfectMaze maze = new PerfectMaze(4, 6, 3, 2);
        maze.printStartLocationChoices();
        Scanner scanner = new Scanner(System.in);
        maze.setStartLocationByRoomId(scanner.nextLong());
        maze.printMaze();
        while (!maze.isEnd()) {
            // print possible moves
            maze.printPossibleDirections();
            // get input
            String typein = scanner.next();
            if (typein.equals("")) {
                System.out.println("INVALID CODE");
            }
            char code = typein.charAt(0);
            if (code == 'n' || code == 's' || code == 'w' || code == 'e') {
                maze.movePlayer(code);
            } else if (code == 'm') {
                // print maze
                maze.printMaze();
            } else if (code == 'p') {
                maze.printPlayerInfo();
            } else {
                System.out.println("INVALID CODE");
            }
        }
    }
}
