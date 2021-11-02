package maze;

import maze.enums.MoveEnum;
import player.Player;

import java.util.List;

/**
 * @author novo
 * @since 2021/10/24
 */
public interface Maze {

    void setPlayer(Player player);

    Player getPlayer();

    void printPlayerInfo();

    void setStartLocation(int x, int y);

    void setGoalLocation(int x, int y);

    void randomStartAndGoal();

    /**
     * get possible directions that the player could move based on the player's current location
     *
     * @return list of possible moves
     */
    List<MoveEnum> getPossibleDirections();

    /**
     * print out possible moves that the player could move
     */
    void printPossibleDirections();

    void movePlayer(MoveEnum moveEnum);

    void movePlayer(char c);

    void showBestRoute();

    /**
     * this is a wrapping maze or not
     */
    boolean isWrapping();

}
