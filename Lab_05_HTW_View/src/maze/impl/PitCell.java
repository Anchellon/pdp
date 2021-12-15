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
public class PitCell extends AbstractCell {
    public PitCell(Location location) {
        super(location);
        this.addType(CellTypeEnum.PIT);
    }

    public PitCell(int x, int y) {
        super(x, y);
        this.addType(CellTypeEnum.PIT);
    }

    @Override
    public String processPlayer(Player player, Maze maze) {
        super.processPlayer(player, maze);
        // game over
        maze.setGameStatus(AbstractMaze.LOSE);
        System.out.println("You fell into a pit!\n" +
                "Better luck next time");
        return "You fell into a pit!\n" +
                "Better luck next time";
    }
}
