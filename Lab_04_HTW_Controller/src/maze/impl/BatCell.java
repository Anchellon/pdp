package maze.impl;

import maze.AbstractCell;
import maze.Location;
import maze.Maze;
import maze.enums.CellTypeEnum;
import player.Player;

import java.util.Random;

/**
 * @author novo
 * @since 2021/11/26
 */
public class BatCell extends AbstractCell {
    // possibility to be carried to another place
    private final double POSSIBILITY = 0.5;

    public BatCell(Location location) {
        super(location);
        this.addType(CellTypeEnum.BAT);
    }

    public BatCell(int x, int y) {
        super(x, y);
        this.addType(CellTypeEnum.BAT);
    }

    @Override
    public void processPlayer(Player player, Maze maze) {
        super.processPlayer(player, maze);
        Random random = new Random();
        if (random.nextDouble() < POSSIBILITY) {
            // Whoa -- you successfully duck superbats that try to grab you
            System.out.println("Whoa -- you successfully duck superbats that try to grab you");
        } else {
            // Snatch -- you are grabbed by superbats and ...
            System.out.println("Snatch -- you are grabbed by superbats and ...");
            // move player to a random location
            maze.movePlayerToRandomRoom();
        }
    }
}
