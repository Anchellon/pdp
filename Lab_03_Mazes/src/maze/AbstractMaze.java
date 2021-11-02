package maze;

import maze.enums.MazeTypeEnum;
import maze.enums.MoveEnum;
import maze.impl.GoldCell;
import maze.impl.NormalCell;
import maze.impl.ThiefCell;
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
    private Map<Cell, Set<Cell>> walls;
    private int numOfWalls;
    private Location startLocation;
    private Location goalLocation;

    // default ratio of these two types of cells
    private static final double DEFAULT_GOLD_CELL_RATIO = 0.2;
    private static final double DEFAULT_THIEF_CELL_RATIO = 0.1;

    /**
     * Constructor
     *
     * @param type         type of maze
     * @param numOfRows    number of rows
     * @param numOfColumns number of columns
     * @param numOfWalls   number of walls
     */
    public AbstractMaze(MazeTypeEnum type, int numOfRows, int numOfColumns, int numOfWalls) {
        if (type == null || numOfRows < 1 || numOfColumns < 1) {
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
        // auto generate maze id
        this.mazeId = CountUtil.createMaze();
        this.walls = new HashMap<>();
    }

    /**
     * generate gold cells and thief cells
     * this will only be called after we have named start and goal positions
     */
    private void generateCells() {
        if (startLocation == null || goalLocation == null) {
            throw new IllegalStateException("have not named start or goal location yet");
        }
        cells = new Cell[numOfRows][numOfColumns];
        // number of cells
        int numOfCells = numOfColumns * numOfRows;
        // get number of gold cells and number of thief cells
        int numOfGoldCells = (int) (numOfCells * DEFAULT_GOLD_CELL_RATIO);
        int numOfThiefCells = (int) (numOfCells * DEFAULT_THIEF_CELL_RATIO);
        // start generating gold cells and thief cells
        while (numOfCells > 2 && numOfGoldCells != 0) {
            if (!generateGoldCell()) {
                continue;
            }
            numOfGoldCells--;
        }
        // same steps to generate thief cells
        while (numOfCells > 2 && numOfThiefCells != 0) {
            if (!generateThiefCell()) {
                continue;
            }
            numOfThiefCells--;
        }
        // generate normal cells
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (cells[i][j] == null) {
                    cells[i][j] = new NormalCell(i, j);
                }
            }
        }
    }


    private static final Random random = new Random();

    /**
     * generate gold cells
     *
     * @return successful to generate or not
     */
    private boolean generateGoldCell() {
        // location = x * numOfColumns + y
        int location = random.nextInt(numOfColumns * numOfRows);
        int x = location / numOfColumns;
        int y = location % numOfColumns;
        if (cells[x][y] != null
                || (x == startLocation.getX() && y == startLocation.getY())
                || (x == goalLocation.getX() && y == goalLocation.getY())) {
            // this cell already exists
            // or it's startLocation
            // or it's goalLocation
            // try other cells
            return false;
        }
        // this cell is empty now
        // generate random gold [0, 100]
        int gold = random.nextInt(10);
        cells[x][y] = new GoldCell(x, y, gold + 1);
        return true;
    }

    /**
     * generate a thief cell in this maze
     */
    private boolean generateThiefCell() {
        // location = x * numOfColumns + y
        int location = random.nextInt(numOfColumns * numOfRows);
        int x = location / numOfColumns;
        int y = location % numOfColumns;
        if (cells[x][y] != null
                || (x == startLocation.getX() && y == startLocation.getY())
                || (x == goalLocation.getX() && y == goalLocation.getY())) {
            // this cell already exists
            // try other cells
            return false;
        }
        // this cell is empty now
        // generate random gold [0, 100]
        cells[x][y] = new ThiefCell(x, y);
        return true;
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
        // record illegal walls
        Map<Cell, Set<Cell>> invalidWalls = new HashMap<>();
        // legal number of walls, start generating
        while (numOfWalls != 0) {
            // randomly choose a cell
            int location = random.nextInt(numOfRows * numOfColumns);
            int x = location / numOfColumns;
            int y = location % numOfColumns;
            // current cell
            Cell currCell = cells[x][y];
            // get adjacent cells
            List<Cell> adjacentCells = getAdjacentCells(currCell);
            boolean success = false;
            for (Cell adj : adjacentCells) {
                // check if there already has a wall between these two cells
                if (walls.containsKey(currCell) && walls.get(currCell).contains(adj)
                        || (invalidWalls.containsKey(currCell) && invalidWalls.get(currCell).contains(adj))) {
                    continue;
                }
                // generate walls
                walls.putIfAbsent(currCell, new HashSet<>());
                walls.putIfAbsent(adj, new HashSet<>());
                walls.get(currCell).add(adj);
                walls.get(adj).add(currCell);
                // check validity
                if (!isValidMaze()) {
                    walls.get(currCell).remove(adj);
                    walls.get(adj).remove(currCell);
                    invalidWalls.putIfAbsent(currCell, new HashSet<>());
                    invalidWalls.putIfAbsent(adj, new HashSet<>());
                    invalidWalls.get(currCell).add(adj);
                    invalidWalls.get(adj).add(currCell);
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
    private List<Cell> getAdjacentCells(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        Location location = cell.getLocation();
        List<Cell> result = new ArrayList<>();
        // check four siblings
        for (int[] dir : dirs) {
            int nextX = location.getX() + dir[0];
            int nextY = location.getY() + dir[1];
            // check if it's a legal location
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                continue;
            }
            result.add(cells[nextX][nextY]);
        }
        return result;
    }

    /**
     * set player
     * we should set start location and goal location before setting player
     */
    @Override
    public void setPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        if (startLocation == null || goalLocation == null) {
            throw new IllegalStateException("maze has not been initialized! " +
                    "Start location and goal location have not been set");
        }
        this.player = player;
        // change player's location
        player.setLocation(startLocation);
        // don't forget to process player when the player enters start location
        cells[startLocation.getX()][startLocation.getY()].processPlayer(player);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void printPlayerInfo() {
        System.out.println("------------------ Player Info ------------------");
        System.out.println("                 id: " + player.getPlayerId());
        System.out.println("               name: " + player.getName());
        System.out.println("           location: [" + player.getLocation().getX() + ", " + player.getLocation().getY() + "]");
        System.out.println("               gold: " + player.getGoldCount());
    }

    /**
     * set start location of this game
     */
    @Override
    public void setStartLocation(int x, int y) {
        startLocation = new Location(x, y);
        if (goalLocation != null) {
            // generate special cells
            initialization();
        }
    }

    /**
     * set goal location of this game
     */
    @Override
    public void setGoalLocation(int x, int y) {
        goalLocation = new Location(x, y);
        if (startLocation != null) {
            // generate special cells
            initialization();
        }
    }

    /**
     * initialize cells and generate walls
     */
    private void initialization() {
        // step 1: generate normal cells
        generateCells();
        // step 2: generate walls
        generateWalls();
    }

    /**
     * randomly generate start and goal locations
     */
    @Override
    public void randomStartAndGoal() {
        // we need at least two cells in this maze
        if (numOfRows * numOfColumns < 2) {
            throw new IllegalStateException("Maze is too small! Unable to auto generate");
        }
        Random random = new Random();
        int startX = random.nextInt(numOfRows);
        int startY = random.nextInt(numOfColumns);
        setStartLocation(startX, startY);

        int goalX = -1;
        int goalY = -1;
        // make sure start location is different from goal location
        while (goalX == -1 || goalX == startX && goalY == startY) {
            goalX = random.nextInt(numOfRows);
            goalY = random.nextInt(numOfColumns);
        }
        setGoalLocation(goalX, goalY);
    }

    /**
     * get player's possible move directions based on its current location
     */
    @Override
    public List<MoveEnum> getPossibleDirections() {
        // get the cell that the player is currently in
        Location playerLocation = player.getLocation();
        Cell currCell = cells[playerLocation.getX()][playerLocation.getY()];
        List<MoveEnum> possibleMoves = new ArrayList<>();
        // get adjacent cells
        for (MoveEnum moveEnum : MoveEnum.getAllDirections()) {
            // get sibling cell
            int nextX = moveEnum.getX() + playerLocation.getX();
            int nextY = moveEnum.getY() + playerLocation.getY();
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                if (this.type == MazeTypeEnum.WRAPPING_ROOM) {
                    // if it's a wrapping maze and step out of the bounds, just add to possibleMoves
                    possibleMoves.add(moveEnum);
                }
                continue;
            }
            // it's a valid location, check if there's wall
            Cell nextCell = cells[nextX][nextY];
            if (walls.containsKey(currCell) && walls.get(currCell).contains(nextCell)) {
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
        List<MoveEnum> possibleDirections = getPossibleDirections();
        StringBuilder sb = new StringBuilder();
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
     * @param moveEnum
     */
    @Override
    public void movePlayer(MoveEnum moveEnum) {
        if (player == null) {
            throw new IllegalStateException("have not set player yet");
        }
        if (moveEnum == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        // get Current location
        Location playerLocation = player.getLocation();
        Cell currCell = cells[playerLocation.getX()][playerLocation.getY()];
        // get next location
        int nextX = playerLocation.getX() + moveEnum.getX();
        int nextY = playerLocation.getY() + moveEnum.getY();
        if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
            if (this.type != MazeTypeEnum.WRAPPING_ROOM) {
                throw new IndexOutOfBoundsException("player has stepped out of bounds");
            } else {
                nextX = (nextX + numOfRows) % numOfRows;
                nextY = (nextY + numOfColumns) % numOfColumns;
            }
        }
        // check if there's wall
        Cell nextCell = cells[nextX][nextY];
        if (walls.containsKey(currCell) && walls.get(currCell).contains(nextCell)) {
            throw new IllegalStateException("cannot enter target cell, there's wall between these two cells");
        }
        // no wall, use next cell to process player
        nextCell.processPlayer(player);
        if (player.getLocation().getX() == goalLocation.getX() && player.getLocation().getY() == goalLocation.getY()) {
            //
            printPlayerInfo();
            throw new RuntimeException("\n\n========== CONGRATULATION! YOU WIN! ==========\n\n");
        }
    }

    /**
     * move player with a key
     *
     * @param c w, a, s, d
     */
    @Override
    public void movePlayer(char c) {
        if (c != 'w' && c != 'a' && c != 's' && c != 'd') {
            throw new IllegalArgumentException("illegal key, should be one of [w, a, s, d]");
        }
        movePlayer(MoveEnum.convertKey(c));
    }

    /**
     * print out best route to get maximum gold based on player's current location
     */
    @Override
    public void showBestRoute() {
        // get current location
        int x = player.getLocation().getX();
        int y = player.getLocation().getY();
        // get current gold
        long currGold = player.getGoldCount();
        List<Location> bestRoute = new ArrayList<>();
        List<Location> route = new ArrayList<>();
        boolean[][] visited = new boolean[numOfRows][numOfColumns];
        getBestRouteBacktrace(currGold, x, y, visited, route, bestRoute);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestRoute.size(); i++) {
            Location location = bestRoute.get(i);
            sb.append("(").append(location.getX()).append(",").append(location.getY()).append(")");
            if (i != bestRoute.size() - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb.toString() + "\n");
        // -------
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < route.size(); i++) {
            Location location = route.get(i);
            sb2.append("(").append(location.getX()).append(",").append(location.getY()).append(")");
            if (i != route.size() - 1) {
                sb2.append(" -> ");
            }
        }
        System.out.println(sb2.toString() + "\n");
    }

    private long maxProfit = 0;

    private long getBestRouteBacktrace(long currGold, int x, int y, boolean[][] visited, List<Location> route, List<Location> bestRoute) {
        // if visited, return
        if (visited[x][y]) {
            return currGold;
        }
        // if we arrive at goal position, then we can't move anymore
        if (x == goalLocation.getX() && y == goalLocation.getY()) {
            return currGold;
        }
        // mark as visited
        visited[x][y] = true;
        // if it's a gold cell, then increase gold
        if (cells[x][y].isGoldCell()) {
            currGold += cells[x][y].getGold();
        }
        // if it's a thief cell, then lose gold
        if (cells[x][y].isThiefCell()) {
            currGold *= 0.9;
        }
        // if we've found a better route, store this route
        if (currGold > maxProfit) {
            maxProfit = currGold;
            bestRoute = new ArrayList<>(route);
        }
        // visit four directions
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            if (nextX < 0 || nextX > numOfRows - 1 || nextY < 0 || nextY > numOfColumns - 1) {
                // if it's a wrapping maze, then we could transfer to the other side
                if (isWrapping()) {
                    nextX = (nextX + numOfRows) % numOfRows;
                    nextY = (nextY + numOfColumns) % numOfColumns;
                } else {
                    continue;
                }
            }
            // there's a wall between next cell and current cell, continue
            if (walls.containsKey(cells[x][y]) && walls.get(cells[x][y]).contains(cells[nextX][nextY])) {
                continue;
            }
            // have not visited, then we visit
            if (!visited[nextX][nextY]) {
                route.add(new Location(nextX, nextY));
                long routeRes = getBestRouteBacktrace(currGold, nextX, nextY, visited, route, bestRoute);
                if (routeRes > currGold) {
                    route.add(new Location(x, y));
                    currGold = routeRes;
                } else {
                    route.remove(route.size() - 1);
                }
            }
        }
        // mark as unvisited
        visited[x][y] = false;
        return currGold;
    }

    @Override
    public boolean isWrapping() {
        // only wrapping room maze is wrapping
        return this.type == MazeTypeEnum.WRAPPING_ROOM;
    }

    /**
     * print maze, for debug using only
     */
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
                    Cell currCell = cells[i][j];
                    Cell upperCell = cells[i - 1][j];
                    if (walls.containsKey(currCell) && walls.get(currCell).contains(upperCell)) {
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
                if (currCell.getLocation().getX() == player.getLocation().getX()
                        && currCell.getLocation().getY() == player.getLocation().getY()) {
                    sb.append(" P ");
                } else if (currCell.isGoldCell()) {
                    // gold cell
                    sb.append(" $ ");
                } else if (currCell.isThiefCell()) {
                    sb.append(" ! ");
                } else if (currCell.getLocation().getX() == goalLocation.getX()
                        && currCell.getLocation().getY() == goalLocation.getY()) {
                    sb.append(" G ");
                } else if (currCell.getLocation().getX() == startLocation.getX()
                        && currCell.getLocation().getY() == startLocation.getY()) {
                    sb.append(" S ");
                } else {
                    sb.append("   ");
                }
                // process right wall
                if (j != numOfColumns - 1) {
                    Cell rightCell = cells[i][j + 1];
                    if (walls.containsKey(currCell) && walls.get(currCell).contains(rightCell)) {
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
     * using dfs to check if we could reach goal location or not
     *
     * @return validity of this maze
     */
    private boolean isValidMaze() {
        boolean[][] visited = new boolean[numOfRows][numOfColumns];
        dfs(visited, 0, 0);
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
    private void dfs(boolean[][] visited, int x, int y) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        Cell currCell = cells[x][y];
        for (int[] dir : dirs) {
            int nextX = dir[0] + x;
            int nextY = dir[1] + y;
            if (nextX >= 0 && nextX <= numOfRows - 1 && nextY >= 0 && nextY <= numOfColumns - 1
                    && !visited[nextX][nextY]
                    && (!walls.containsKey(currCell) || !walls.get(currCell).contains(cells[nextX][nextY]))) {
                dfs(visited, nextX, nextY);
            }
        }
    }
}
