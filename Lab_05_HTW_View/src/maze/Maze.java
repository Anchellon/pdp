package maze;

import maze.dto.LocationInfo;
import maze.dto.PlayerInfo;
import maze.enums.CellTypeEnum;
import maze.enums.MoveEnum;
import player.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    String movePlayer(MoveEnum moveEnum);

    String movePlayerToRandomRoom();

    String movePlayer(char c);

    Location getRandomRoom();

    int getGameStatus();

    void setGameStatus(int status);

    void printStartLocationChoices();

    Long[] getStartLocations();

    void setStartLocationByRoomId(long cellId);

    String shoot(char c, int distance);

    void setNumOfArrows(int numOfArrows);

    void printMaze();

    boolean isWinnable();

    boolean[][] getVisitStatus();

    LocationInfo getLocationInfo(Location location);

    boolean isVisible(Location location);

    boolean isAdjacent(Location loc1, Location loc2);

    Set<CellTypeEnum> getSurroundingRoomTypes(Location location);

    void restartGame();

    void switchPlayer(PlayerInfo player);

    Long getStartRoomId();
}
