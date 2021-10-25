package maze;

import maze.enums.MoveEnum;

import java.util.List;

/**
 * @author novo
 * @since 2021/10/24
 */
public class AbstractMaze implements Maze{

    public void move(MoveEnum moveEnum) {
    }

    @Override
    public List<MoveEnum> getDirections() {
        return null;
    }
}
