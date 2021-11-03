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
     * this is a wrapping maze or not
     */
    boolean isWrapping();

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

    /**
     * show best route for this player to get gold based on its current location
     */
    void showBestRoute();

}
