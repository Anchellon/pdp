package maze.impl;

import maze.AbstractCell;
import maze.AbstractMaze;
import maze.Location;
import maze.Maze;
import maze.enums.CellTypeEnum;
import player.Player;

/**
 * @author novo
 * @since 2021/11/26
 */
public class WumpusCell extends AbstractCell {

    public WumpusCell(Location location) {
        super(location);
        this.addType(CellTypeEnum.WUMPUS);
    }

    public WumpusCell(int x, int y) {
        super(x, y);
        this.addType(CellTypeEnum.WUMPUS);
    }

    @Override
    public void processPlayer(Player player, Maze maze) {
        super.processPlayer(player, maze);
        // game over
        maze.setGameStatus(AbstractMaze.LOSE);
        System.out.println("Chomp, chomp, chomp, thanks for feeding the Wumpus!\n" +
                "Better luck next time");
    }
}
