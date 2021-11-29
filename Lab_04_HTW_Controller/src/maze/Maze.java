package maze;

import maze.enums.MoveEnum;
import player.Player;

import java.util.List;

/**
 * @author novo
 * @since 2021/10/24
 */
public interface Maze {

    Player getPlayer();

    void printPlayerInfo();

//    void setStartLocation(int x, int y);

    /**
     * this is a wrapping maze or not
     */
    boolean isWrapping();

    /**
     * get possible directions that the player could move based on the player's current location
     *
     * @return list of possible moves
     */
    List<MoveEnum> getPossibleDirections(Location location);

    /**
     * print out possible moves that the player could move
     */
    void printPossibleDirections();

    void movePlayer(MoveEnum moveEnum);

    void movePlayerToRandomRoom();

    void movePlayer(char c);

    Location getRandomRoom();

    boolean isEnd();

    void setEnd(boolean end);

    void printStartLocationChoices();

    void setStartLocationByRoomId(long cellId);

    void shoot(char c, int distance);

    void setNumOfArrows(int numOfArrows);

    void printMaze();
}
