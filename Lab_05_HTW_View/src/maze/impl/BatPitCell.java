package maze.impl;

import maze.AbstractMaze;
import maze.Location;
import maze.Maze;
import maze.enums.CellTypeEnum;
import player.Player;

/**
 * @author novo
 * @since 2021/11/26
 */
public class BatPitCell extends BatCell{
    public BatPitCell(Location location) {
        super(location);
        this.addType(CellTypeEnum.PIT);
    }

    public BatPitCell(int x, int y) {
        super(x, y);
        this.addType(CellTypeEnum.PIT);
    }

    @Override
    public void processPlayer(Player player, Maze maze) {
        // we firstly use parent's process
        super.processPlayer(player, maze);
        // if we're still here, then we will die
        if (player.getLocation().equals(this.getLocation())) {
            // we will fall into pit
            maze.setGameStatus(AbstractMaze.LOSE);
            System.out.println("You fell into a pit!\n" +
                    "Better luck next time");
        }
    }
}
