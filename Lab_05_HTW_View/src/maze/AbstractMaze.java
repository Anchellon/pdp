package maze;

import maze.enums.CellTypeEnum;
import maze.enums.MazeTypeEnum;
import maze.enums.MoveEnum;
import maze.impl.*;
import player.Player;
import util.CountUtil;

import java.util.*;

/**
 * @author novo
 * @since 2021/10/24
 */
public class AbstractMaze implements Maze {
    private final long mazeId;
    private final MazeTypeEnum type;
    private Player player;
    private final int numOfRows;
    private final int numOfColumns;
    private Cell[][] cells;
    // different: using location to store walls
    private final Map<Location, Set<Location>> walls;
    private int numOfWalls;

    // list of rooms
    List<Location> rooms;

    private boolean end;
    private final int numOfWumpus = 1;
    private int numOfPit;
    private int numOfBat;

    // location of wumpus
    private Location wumpusLoc;

    /**
     * Constructor
     *
     * @param type         type of maze
     * @param numOfRows    number of rows
     * @param numOfColumns number of columns
     * @param numOfWalls   number of walls
     */
    public AbstractMaze(MazeTypeEnum type,
                        int numOfRows,
                        int numOfColumns,
                        int numOfWalls,
                        int numOfBat,
                        int numOfPit) {
        if (type == null || numOfRows < 1 || numOfColumns < 1 || numOfBat < 0 || numOfPit < 0) {
            throw new IllegalArgumentException("illegal arguments");
        }
        int maxWall = numOfColumns * (numOfRows - 1) + numOfRows * (numOfColumns - 1) - numOfColumns * numOfRows + 1;
        if (numOfWalls < 0 || numOfWalls > maxWall) {
            throw new IllegalArgumentException("illegal wall numbers");
        }
        this.type = type;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.numOfWalls = numOfWalls;
        this.numOfBat = numOfBat;
        this.numOfPit = numOfPit;
        // auto generate maze id
        this.mazeId = CountUtil.createMaze();
        this.walls = new HashMap<>();
        // generate cells
        initialization();
        // create new player
        this.player = new Player();
    }

    /**
     * generate cells
     * For each cell, we have to check how many walls that are around this cell.
     * If numOfWalls == 2, then this cell will be a tunnel.
     * Otherwise, it will be a candidate cell for special cells.
     */
    private void generateCells() {
        cells = new Cell[numOfRows][numOfColumns];
        // number of cells
        int numOfCells = numOfColumns * numOfRows;
        // candidate locations for special cells
        rooms = new LinkedList<>();
        // iterate over all locations
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                // check num of walls
                Location currLocation = new Location(i, j);
                if (isRoom(currLocation)) {
                    // if this cell doesn't have walls, or num of walls is not 2
                    // then it will be a candidate location
                    rooms.add(currLocation);
                } else {
                    // it's a tunnel
                    cells[i][j] = new NormalCell(currLocation);
                }
            }
        }
        // check the number of candidates
        if (rooms.size() < (numOfWumpus + numOfPit + numOfBat)) {
            throw new IllegalStateException("Invalid Maze -- Cannot meet the requirements");
        }
        // start generating wumpus
        Random random = new Random();
        Location wumpusLocation = rooms.get(random.nextInt(rooms.size()));
        this.wumpusLoc = wumpusLocation;
        cells[wumpusLocation.getX()][wumpusLocation.getY()] = new WumpusCell(wumpusLocation);
        // start generating pit
        while (numOfPit != 0) {
            Location pitLocation = rooms.get(random.nextInt(rooms.size()));
            if (pitLocation.equals(wumpusLocation)) {
                continue;
            }
            cells[pitLocation.getX()][pitLocation.getY()] = new PitCell(pitLocation);
            numOfPit--;
        }
        // start generating bat
        while (numOfBat != 0) {
            Location batLocation = rooms.get(random.nextInt(rooms.size()));
            if (batLocation.equals(wumpusLocation)) {
                continue;
            }
            if (cells[batLocation.getX()][batLocation.getY()] != null &&
                    cells[batLocation.getX()][batLocation.getY()].getType().contains(CellTypeEnum.PIT)) {
                cells[batLocation.getX()][batLocation.getY()] = new BatPitCell(batLocation);
            } else {
                cells[batLocation.getX()][batLocation.getY()] = new BatCell(batLocation);
            }
            numOfBat--;
        }
        // process remaining rooms
        for (Location loc : rooms) {
            if (cells[loc.getX()][loc.getY()] == null) {
                cells[loc.getX()][loc.getY()] = new NormalCell(loc);
            }
        }
    }

    /**
     * generate numOfWalls walls
     */
    private void generateWalls() {
        // check if number of walls is valid
        int edges = numOfRows * (numOfColumns - 1) + numOfColumns * (numOfRows - 1);
        int maxWalls = edges - numOfRows * numOfColumns + 1;
        if (numOfWalls > maxWalls
                || numOfWalls < 0
                || (this.type != MazeTypeEnum.PERFECT && numOfWalls == maxWalls)) {
            // 1. 0 <= numOfWalls <= maxWalls
            // 2. if it's not a perfect maze, then it cannot have maxWalls of walls
            throw new IllegalArgumentException("number of walls is illegal");
        }
        Random random = new Random();
        // record illegal walls
        Map<Location, Set<Location>> invalidWalls = new HashMap<>();
        // legal number of walls, start generating
        while (numOfWalls != 0) {
            // randomly choose a cell
            int location = random.nextInt(numOfRows * numOfColumns);
            int x = location / numOfColumns;
            int y = location % numOfColumns;
            Location currLocation = new Location(x, y);
            // get adjacent cells
            List<Location> adjacentCells = getAdjacentLocations(x, y);
            boolean success = false;
            for (Location adj : adjacentCells) {
                // check if there already has a wall between these two cells
                if (walls.containsKey(currLocation) && walls.get(currLocation).contains(adj)
                        || (invalidWalls.containsKey(currLocation) && invalidWalls.get(currLocation).contains(adj))) {
                    continue;
                }
                // generate walls
                walls.putIfAbsent(currLocation, new HashSet<>());
                walls.putIfAbsent(adj, new HashSet<>());
                walls.get(currLocation).add(adj);
                walls.get(adj).add(currLocation);
                // check validity
                if (!isValidMaze()) {
                    walls.get(currLocation).remove(adj);
                    walls.get(adj).remove(currLocation);
                    invalidWalls.putIfAbsent(currLocation, new HashSet<>());
                    invalidWalls.putIfAbsent(adj, new HashSet<>());
                    invalidWalls.get(currLocation).add(adj);
                    invalidWalls.get(adj).add(currLocation);
                    continue;
                }
                // only generate one wall
                success = true;
                break;
            }
            if (success) {
                numOfWalls--;
            }
        }
    }

    // four directions
    private static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * get adjacent cells of target cell
     */
    private List<Location> getAdjacentLocations(int x, int y) {
        if (x < 0 || x > numOfRows - 1 || y < 0 || y > numOfColumns - 1) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        List<Location> result = new ArrayList<>();
        // check four siblings
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            // check if it's a legal location
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                continue;
            }
            result.add(new Location(nextX, nextY));
        }
        return result;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void printPlayerInfo() {
        System.out.println("------------------ Player Info ------------------");
        System.out.println("                 id: " + player.getPlayerId());
        System.out.println("           location: [" + player.getLocation().getX() + ", " + player.getLocation().getY() + "]");
        System.out.println("   number of arrows: " + player.getNumOfArrow());
    }

    /**
     * initialize cells and generate walls
     */
    private void initialization() {
        // step 1: generate walls
        generateWalls();
        // step 2: generate cells
        generateCells();
    }

    /**
     * get player's possible move directions based on its current location
     */
    @Override
    public List<MoveEnum> getPossibleDirections(Location location) {
        // get the cell that the player is currently in
        List<MoveEnum> possibleMoves = new ArrayList<>();
        // get adjacent cells
        for (MoveEnum moveEnum : MoveEnum.getAllDirections()) {
            // get sibling cell
            int nextX = moveEnum.getX() + location.getX();
            int nextY = moveEnum.getY() + location.getY();
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                if (this.type == MazeTypeEnum.WRAPPING) {
                    // if it's a wrapping maze and step out of the bounds, just add to possibleMoves
                    possibleMoves.add(moveEnum);
                }
                continue;
            }
            // it's a valid location, check if there's wall
            Location nextLocation = new Location(nextX, nextY);
            if (walls.containsKey(location) && walls.get(location).contains(nextLocation)) {
                // has wall, cannot move
                continue;
            }
            // no wall
            possibleMoves.add(moveEnum);
        }
        return possibleMoves;
    }

    /**
     * print out possible directions
     */
    @Override
    public void printPossibleDirections() {
        List<MoveEnum> possibleDirections = getPossibleDirections(player.getLocation());
        StringBuilder sb = new StringBuilder();
        // check possible adjacent cells -- wumpus, bat, pit
        Set<CellTypeEnum> surroundingTypes = new HashSet<>();
        for (MoveEnum moveEnum : possibleDirections) {
            int nextX = player.getLocation().getX() + moveEnum.getX();
            int nextY = player.getLocation().getY() + moveEnum.getY();
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                if (isWrapping()) {
                    nextX = (nextX + numOfRows) % numOfRows;
                    nextY = (nextY + numOfColumns) % numOfColumns;
                } else {
                    continue;
                }
            }
            // get next possible direction
            Location nextLoc = new Location(nextX, nextY);
            // get next adjacent room location
            Location nextRoomLoc = getNextLocation(player.getLocation(), nextLoc);
            // check if it's a special room
            if (nextRoomLoc == null) {
                continue;
            }
            surroundingTypes.addAll(cells[nextRoomLoc.getX()][nextRoomLoc.getY()].getType());
        }
        // check all surrounding types
        if (surroundingTypes.contains(CellTypeEnum.WUMPUS)) {
            sb.append("You smell a Wumpus!\n");
        }
        if (surroundingTypes.contains(CellTypeEnum.PIT)) {
            sb.append("You feel a draft\n");
        }
        sb.append("Possible moves: ");
        for (MoveEnum moveEnum : possibleDirections) {
            sb.append(moveEnum.toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    /**
     * move player with moveEnum
     *
     */
    @Override
    public void movePlayer(MoveEnum moveEnum) {
        if (player.getLocation() == null) {
            throw new IllegalStateException("have not set player start location");
        }
        if (moveEnum == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        // get current location
        Location playerLocation = player.getLocation();
        // get next location
        int nextX = playerLocation.getX() + moveEnum.getX();
        int nextY = playerLocation.getY() + moveEnum.getY();
        if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
            if (this.type != MazeTypeEnum.WRAPPING) {
                throw new IndexOutOfBoundsException("player has stepped out of bounds");
            } else {
                nextX = (nextX + numOfRows) % numOfRows;
                nextY = (nextY + numOfColumns) % numOfColumns;
            }
        }
        // check if there's wall
        Location nextLocation = new Location(nextX, nextY);
        if (walls.containsKey(playerLocation) && walls.get(playerLocation).contains(nextLocation)) {
            System.out.println("cannot enter target cell, there's wall between these two cells");
            return;
        }
        // no wall, get next room
        Location nextRoom = getNextLocation(playerLocation, nextLocation);
        if (nextRoom == null) {
            throw new IllegalStateException("No next room");
        }
        cells[nextRoom.getX()][nextRoom.getY()].processPlayer(player, this);
    }

    /**
     * we may enter a tunnel, and the real next location should be the exit of tunnel
     */
    private Location getNextLocation(Location prevLoc, Location currLoc) {
        if (prevLoc == null || currLoc == null) {
            throw new IllegalArgumentException("location is null");
        }
        if (currLoc.getX() < 0 || currLoc.getX() > numOfRows - 1 || currLoc.getY() < 0 || currLoc.getY() > numOfColumns - 1) {
            return null;
        }
        // if it's a room, then just return this
        if (isRoom(currLoc)) {
            return currLoc;
        }
        List<MoveEnum> possibleDirections = getPossibleDirections(currLoc);
        // it's a tunnel
        // visit four directions, get two exits
        for (MoveEnum move : possibleDirections) {
            int nextX = currLoc.getX() + move.getX();
            int nextY = currLoc.getY() + move.getY();
            Location adj = new Location(nextX, nextY);
            if (!adj.equals(prevLoc)) {
                // there's no wall, and this location is not where we came from
                return getNextLocation(currLoc, adj);
            }
        }
        return null;
    }

    @Override
    public void movePlayerToRandomRoom() {
        Location randomRoom = getRandomRoom();
        cells[randomRoom.getX()][randomRoom.getY()].processPlayer(player, this);
    }

    /**
     * move player with a key
     *
     * @param c w, a, s, d
     */
    @Override
    public void movePlayer(char c) {
        if (c != 'w' && c != 's' && c != 'e' && c != 'n') {
            throw new IllegalArgumentException("illegal key, should be one of [w, s, e, n]");
        }
        movePlayer(MoveEnum.convertKey(c));
    }

    @Override
    public Location getRandomRoom() {
        // randomly choose a room
        Random random = new Random();
        return rooms.get(random.nextInt(rooms.size()));
    }

    @Override
    public boolean isWrapping() {
        // only wrapping room maze is wrapping
        return this.type == MazeTypeEnum.WRAPPING;
    }

    /**
     * print maze, for debug using only
     */
    @Override
    public void printMaze() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numOfColumns; j++) {
            sb.append("+— —");
        }
        sb.append("+");
        sb.append("\n");
        for (int i = 0; i < numOfRows; i++) {
            if (i != 0) {
                // print upper bounds of each row
                for (int j = 0; j < numOfColumns; j++) {
                    Location currLoc = new Location(i, j);
                    Location upperLoc = new Location(i - 1, j);
                    if (walls.containsKey(currLoc) && walls.get(currLoc).contains(upperLoc)) {
                        // has wall
                        sb.append("+---");
                    } else {
                        // no wall
                        sb.append("+   ");
                    }
                }
                sb.append("+\n");
            }
            sb.append("|");
            for (int j = 0; j < numOfColumns; j++) {
                Cell currCell = cells[i][j];
                Location currLoc = new Location(i, j);
                if (currLoc.equals(player.getLocation())) {
                    sb.append(" * ");
                } else if (currCell.getType().size() == 0) {
                    sb.append("   ");
                } else if (currCell.getType().size() == 2) {
                    sb.append("P+B");
                } else {
                    if (currCell.getType().contains(CellTypeEnum.WUMPUS)) {
                        sb.append(" W ");
                    } else if (currCell.getType().contains(CellTypeEnum.PIT)) {
                        sb.append(" P ");
                    } else {
                        sb.append(" B ");
                    }
                }
                // process right wall
                if (j != numOfColumns - 1) {
                    Location rightLoc = new Location(i, j + 1);
                    if (walls.containsKey(currLoc) && walls.get(currLoc).contains(rightLoc)) {
                        // has wall
                        sb.append("|");
                    } else {
                        sb.append(" ");
                    }
                }
            }
            sb.append("|\n");
        }
        // last lower bound
        for (int j = 0; j < numOfColumns; j++) {
            sb.append("+— —");
        }
        sb.append("+");
        sb.append("\n");
        System.out.println(sb.toString());
    }

    /**
     * using dfs to check if we could reach all locations or not
     *
     * @return validity of this maze
     */
    private boolean isValidMaze() {
        boolean[][] visited = new boolean[numOfRows][numOfColumns];
        checkValid(visited, 0, 0);
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (!visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * using dfs to check the validity of this maze
     * only when all cells could be visited, this maze is valid
     */
    private void checkValid(boolean[][] visited, int x, int y) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        Location currLoc = new Location(x, y);
        for (int[] dir : dirs) {
            int nextX = dir[0] + x;
            int nextY = dir[1] + y;
            Location nextLoc = new Location(nextX, nextY);
            if (nextX >= 0 && nextX <= numOfRows - 1 && nextY >= 0 && nextY <= numOfColumns - 1
                    && !visited[nextX][nextY]
                    && (!walls.containsKey(currLoc) || !walls.get(currLoc).contains(nextLoc))) {
                checkValid(visited, nextX, nextY);
            }
        }
    }

    @Override
    public boolean isEnd() {
        return this.end;
    }

    @Override
    public void setEnd(boolean end) {
        this.end = end;
    }

    @Override
    public void printStartLocationChoices() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please choose one start location from [");
        // iterate over all rooms
        for (Location roomLoc : rooms) {
            sb.append(cells[roomLoc.getX()][roomLoc.getY()].getId()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]: ");
        System.out.println(sb.toString());
    }

    @Override
    public void setStartLocationByRoomId(long cellId) {
        // visit all rooms
        for (Location roomLoc : rooms) {
            if (cells[roomLoc.getX()][roomLoc.getY()].getId() == cellId) {
                cells[roomLoc.getX()][roomLoc.getY()].processPlayer(player, this);
                return;
            }
        }
        throw new IllegalArgumentException("Room doesn't exist");
    }

    /**
     * shoot an arrow with a key and a distance
     */
    @Override
    public void shoot(char c, int distance) {
        if (distance < 1 || distance > 5) {
            throw new IllegalArgumentException("illegal distance");
        }
        if (c != 'w' && c != 'n' && c != 'e' && c != 's') {
            throw new IllegalArgumentException("illegal direction");
        }
        // convert key to direction
        MoveEnum direction = MoveEnum.convertKey(c);
        Location currLoc = player.getLocation();
        int nextX = currLoc.getX() + direction.getX();
        int nextY = currLoc.getY() + direction.getY();
        if (isWrapping()) {
            // check is a wrapping maze or not
            nextX = (nextX + numOfRows) % numOfRows;
            nextY = (nextY + numOfColumns) % numOfColumns;
        }
        Location nextLoc = new Location(nextX, nextY);
        if (shootInDirection(currLoc, nextLoc, distance)) {
            end = true;
            System.out.println("Hee hee hee, you got the wumpus!\n" +
                    "Next time you won't be so lucky");
        } else {
            System.out.println("You missed the shoot!");
            player.setNumOfArrow(player.getNumOfArrow() - 1);
            if (player.getNumOfArrow() == 0) {
                end = true;
                System.out.println("Run out of arrows, you lose");
            }
        }
    }

    /**
     * shoot an arrow in a specific direction and a specific distance
     */
    private boolean shootInDirection(Location prevLoc, Location currLoc, int distance) {
        if (isRoom(currLoc)) {
            if (--distance == 0) {
                // check if this is a wumpus room
                return cells[currLoc.getX()][currLoc.getY()].getType().contains(CellTypeEnum.WUMPUS);
            }
            // we have to move further
            // firstly, we should know what the direction is
            if (!isWrapping()) {
                // if it's not a wrapping maze
                // they are adjacent
                int moveX = currLoc.getX() - prevLoc.getX();
                int moveY = currLoc.getY() - prevLoc.getY();
                Location nextLoc = new Location(currLoc.getX() + moveX, currLoc.getY() + moveY);
                // check if there's a wall between next room and current room
                if (nextLoc.getX() < 0 || nextLoc.getX() > numOfRows - 1 ||
                        nextLoc.getY() < 0 || nextLoc.getY() > numOfColumns - 1 ||
                        (walls.containsKey(currLoc) && walls.get(currLoc).contains(nextLoc))) {
                    // there's a wall
                    return false;
                }
                return shootInDirection(currLoc, nextLoc, distance);
            } else {
                // it's a wrapping maze
                int moveX = currLoc.getX() - prevLoc.getX();
                int moveY = currLoc.getY() - prevLoc.getY();
                Location nextLoc = new Location((currLoc.getX() + moveX + numOfRows) % numOfRows,
                        (currLoc.getY() + moveY + numOfColumns) % numOfColumns);
                if (walls.containsKey(currLoc) && walls.get(currLoc).contains(nextLoc)) {
                    // there's a wall
                    return false;
                }
                return shootInDirection(currLoc, nextLoc, distance);
            }
        } else {
            // it's a tunnel, we should go to next room
            List<MoveEnum> possibleDirections = getPossibleDirections(currLoc);
            // visit four directions, get two exits
            for (MoveEnum move : possibleDirections) {
                int nextX = currLoc.getX() + move.getX();
                int nextY = currLoc.getY() + move.getY();
                Location adj = new Location(nextX, nextY);
                if (!adj.equals(prevLoc)) {
                    // there's no wall, and this location is not where we came from
                    return shootInDirection(currLoc, adj, distance);
                }
            }
        }
        return false;
    }

    /**
     * target location is a room or tunnel
     *
     * 0 0 0
     * 0 0 0
     */
    private boolean isRoom(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("location is null");
        }
        if (this.isWrapping() ||
                (location.getX() > 0 && location.getX() < numOfRows - 1
                        && location.getY() > 0 && location.getY() < numOfColumns - 1)) {
            return !walls.containsKey(location) || walls.get(location).size() != 2;
        }
        // it's a border
        if ((location.getX() == 0 && location.getY() == 0)
                || (location.getX() == numOfRows - 1 && location.getY() == 0)
                || (location.getX() == 0 && location.getY() == numOfColumns - 1)
                || (location.getX() == numOfRows - 1 && location.getY() == numOfColumns - 1)) {
            // if it's a vertex
            return walls.containsKey(location) && walls.get(location).size() == 1;
        } else {
            // it's a normal border
            return !walls.containsKey(location) || walls.get(location).size() != 1;
        }
    }

    @Override
    public void setNumOfArrows(int numOfArrows) {
        if (numOfArrows <= 0) {
            throw new IllegalArgumentException("num of arrows cannot smaller than 1");
        }
        this.player.setNumOfArrow(numOfArrows);
    }

    /**
     * check the player could have a chance to win or not
     * <p>
     * step1: check four directions of wumpus
     * <p>
     * step2: check adjacent rooms around player
     * <p>
     * winnable:
     * +— —+— —+— —+— —+
     * |     W         |
     * +   +   +---+   +
     * |   |P+B    |   |
     * +   +   +   +---+
     * |   |   | *     |
     * +   +   +   +   +
     * |   | B | B | P |
     * +— —+— —+— —+— —+
     *
     * unwinnable:
     * +— —+— —+— —+
     * |       | B |
     * +   +   +   +
     * |   | P     |
     * +   +   +   +
     * | P | W | * |
     * +— —+— —+— —+
     */
    @Override
    public boolean isWinnable() {
        // step1: check four directions of wumpus
        Set<Location> safeRooms = new HashSet<>();
        // check four directions
        for (int[] dir : dirs) {
            int nextX = wumpusLoc.getX() + dir[0];
            int nextY = wumpusLoc.getY() + dir[1];
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                if (!isWrapping()) {
                    continue;
                }
                nextX = (nextX + numOfRows) % numOfRows;
                nextY = (nextY + numOfColumns) % numOfColumns;
            }
            Location nextLoc = new Location(nextX, nextY);
            // check if there's wall
            if (walls.containsKey(wumpusLoc) && walls.get(wumpusLoc).contains(nextLoc)) {
                continue;
            }
            checkWumpus(wumpusLoc, nextLoc, safeRooms);
        }
        if (safeRooms.isEmpty()) {
            // if there's no safe room, then just return false
            return false;
        }
//        System.out.println(safeRooms);
        // step2: check adjacent rooms around player
        return checkPlayer(player.getLocation(), new HashSet<>(), safeRooms);
    }

    /**
     * check safe rooms around wumpus
     */
    private void checkWumpus(Location prevLoc, Location currLoc, Set<Location> safeRooms) {
        // if we back to the start location, then stop
        if (currLoc.equals(wumpusLoc)) {
            return;
        }
        if (isRoom(currLoc)) {
            if (cells[currLoc.getX()][currLoc.getY()].getType().isEmpty()) {
                // if it's a room
                safeRooms.add(currLoc);
            }
            // we have to move further
            // firstly, we should know what the direction is
            if (!isWrapping()) {
                // if it's not a wrapping maze
                // they are adjacent
                int moveX = currLoc.getX() - prevLoc.getX();
                int moveY = currLoc.getY() - prevLoc.getY();
                Location nextLoc = new Location(currLoc.getX() + moveX, currLoc.getY() + moveY);
                // check if there's a wall between next room and current room
                if (nextLoc.getX() < 0 || nextLoc.getX() > numOfRows - 1 ||
                        nextLoc.getY() < 0 || nextLoc.getY() > numOfColumns - 1 ||
                        (walls.containsKey(currLoc) && walls.get(currLoc).contains(nextLoc))) {
                    // there's a wall
                    return;
                }
                checkWumpus(currLoc, nextLoc, safeRooms);
            } else {
                // it's a wrapping maze
                int moveX = currLoc.getX() - prevLoc.getX();
                int moveY = currLoc.getY() - prevLoc.getY();
                Location nextLoc = new Location((currLoc.getX() + moveX + numOfRows) % numOfRows,
                        (currLoc.getY() + moveY + numOfColumns) % numOfColumns);
                if (walls.containsKey(currLoc) && walls.get(currLoc).contains(nextLoc)) {
                    // there's a wall
                    return;
                }
                checkWumpus(currLoc, nextLoc, safeRooms);
            }
        } else {
            // it's a tunnel, we should go to next room
            List<MoveEnum> possibleDirections = getPossibleDirections(currLoc);
            // visit four directions, get two exits
            for (MoveEnum move : possibleDirections) {
                int nextX = currLoc.getX() + move.getX();
                int nextY = currLoc.getY() + move.getY();
                Location adj = new Location(nextX, nextY);
                if (!adj.equals(prevLoc)) {
                    // there's no wall, and this location is not where we came from
                    checkWumpus(currLoc, adj, safeRooms);
                }
            }
        }
    }

    /**
     * dfs from player's location
     */
    private boolean checkPlayer(Location currLoc, Set<Location> visited, Set<Location> safeRooms) {
        // if this location has been visited
        if (visited.contains(currLoc)) {
            return false;
        }
        visited.add(currLoc);
        // if it's a safe room, or it's a bat room
        if (safeRooms.contains(currLoc) ||
                cells[currLoc.getX()][currLoc.getY()].getType().contains(CellTypeEnum.BAT)) {
//            System.out.println(currLoc);
            return true;
        }
        // visit adjacent rooms
        List<MoveEnum> possibleDirections = getPossibleDirections(currLoc);
        for (MoveEnum move : possibleDirections) {
            int nextX = currLoc.getX() + move.getX();
            int nextY = currLoc.getY() + move.getY();
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                if (!isWrapping()) {
                    return false;
                }
                nextX = (nextX + numOfRows) % numOfRows;
                nextY = (nextY + numOfColumns) % numOfColumns;
            }
            Location nextLoc = new Location(nextX, nextY);
            // check next location
            if (checkPlayer(nextLoc, visited, safeRooms)) {
                return true;
            }
        }
        return false;
    }
}
