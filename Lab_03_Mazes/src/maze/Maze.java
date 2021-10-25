package maze;

import maze.enums.MoveEnum;

import java.util.List;

/**
 * @author novo
 * @since 2021/10/24
 */
public interface Maze {

    List<MoveEnum> getDirections();
}
