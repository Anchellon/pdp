package view;

import image.ImageUtil;
import maze.AbstractMaze;
import maze.Location;
import maze.Maze;
import maze.dto.LocationInfo;
import maze.dto.PlayerInfo;
import maze.enums.CellTypeEnum;
import maze.enums.MoveEnum;
import maze.impl.NonWrappingRoomMaze;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * @author novo
 * @since 2021/12/9
 */
public class GameFrame extends JPanel implements ActionListener {
    private Maze maze;
    // game setting
    private int numOfRows;
    private int numOfCols;
    private int numOfWalls;
    private int numOfBats;
    private int numOfPits;
    private int numOfArrows;

    private JPanel infoPanel;
    private JPanel gamePanel;

    // parent frame
    private MenuView parent;

    private JButton[] buttons;

    private PlayerInfo[] players;
    private int currPlayerIndex = 0;

    public GameFrame(MenuView parent) {
        this.parent = parent;
        // divide this frame to two parts
        this.setSize(400, 300);
        this.setLayout(null);
    }

    public void init(int numOfRows,
                     int numOfCols,
                     int numOfWalls,
                     int numOfBats,
                     int numOfPits,
                     int numOfArrows) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
        this.numOfWalls = numOfWalls;
        this.numOfBats = numOfBats;
        this.numOfPits = numOfPits;
        this.numOfArrows = numOfArrows;
        // create new game
        createNewGame();
        // init keyboard listener
        initKeyboardListener();
    }

    private void createNewGame() {
        this.removeAll();
        // create maze
        maze = new NonWrappingRoomMaze(numOfRows, numOfCols, numOfWalls, numOfBats, numOfPits);

        // reset currPlayerIndex
        currPlayerIndex = 0;

        // two player
        if (!selectNumOfPlayers()) {
            parent.changeView(MenuView.MENU_VIEW);
            return;
        }
        // select start locations
        if (!selectStartLocation()) {
            parent.changeView(MenuView.MENU_VIEW);
            return;
        }

        // change to game view
        parent.changeView(MenuView.GAME_VIEW);

        // ---- DEBUG ----
        maze.printMaze();
        // ---- DEBUG ----

        // init info panel
        initInfoPanel();
        // init buttons
        initButtons();
        // init game panel
        initMazeView();

        this.revalidate();
        this.repaint();
    }

    private boolean selectNumOfPlayers() {
        Integer[] playerChoices = new Integer[]{1, 2};
        Integer numOfPlayers = (Integer)JOptionPane.showInputDialog(this,
                "SELECT NUMBER OF PLAYERS",
                "GAME SETTING",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(),
                playerChoices,
                playerChoices[0]);
        if (numOfPlayers == null) {
            parent.changeView(MenuView.MENU_VIEW);
            return false;
        }
        // init player array
        players = new PlayerInfo[numOfPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new PlayerInfo();
            players[i].setPlayer(new Player());
            players[i].setGameStatus(AbstractMaze.ALIVE);
            players[i].getPlayer().setNumOfArrow(this.numOfArrows);
        }
        return true;
    }

    private boolean selectStartLocation() {
        Long[] startLocations = maze.getStartLocations();
        Object o = JOptionPane.showInputDialog(this,
                "SELECT A START LOCATION",
                "GAME SETTING",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(),
                startLocations,
                startLocations[0]);
        if (o == null) {
            parent.changeView(MenuView.MENU_VIEW);
            return false;
        }
        // init all players' start locations
        for (PlayerInfo player : players) {
            maze.switchPlayer(player);
            maze.setStartLocationByRoomId((Long) o);
        }
        maze.switchPlayer(players[0]);
        return true;
    }


    /**
     * step1: check if current player wins
     * step2: one player lose, check if there's still an alive player
     */
    private void gameOver() {
        if (maze.getGameStatus() == AbstractMaze.WIN) {
            players[currPlayerIndex].setGameStatus(AbstractMaze.WIN);
            int result = JOptionPane.showConfirmDialog(this, "YOU WIN! Back to menu?", "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                parent.changeView(MenuView.MENU_VIEW);
            }
            return;
        } else {
            // this player lose
            players[currPlayerIndex].setGameStatus(AbstractMaze.LOSE);
        }
        // check if there's alive player
        if (isPlayerAlive()) {
            JOptionPane.showMessageDialog(this, "YOU LOSE!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
        } else {
            // no alive players
            int result = JOptionPane.showConfirmDialog(this, "NO PLAYER ALIVE! Back to menu?", "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                parent.changeView(MenuView.MENU_VIEW);
            }
        }
    }

    private void initInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.removeAll();
        infoPanel.setLayout(new GridLayout(1, 2));
        infoPanel.setSize(250, 50);
        infoPanel.setLocation(0, 0);
        // player info panel
        JPanel playerInfoPanel = new JPanel(new GridLayout(2, 1));
        JLabel currentPlayerInfoLabel = new JLabel("Player" + (currPlayerIndex + 1));
        currentPlayerInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayerInfoLabel.setBackground(Color.CYAN);
        currentPlayerInfoLabel.setOpaque(true);

        JLabel arrowLabel = new JLabel("Arrow: " + maze.getPlayer().getNumOfArrow());
        arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrowLabel.setBackground(Color.ORANGE);
        arrowLabel.setOpaque(true);

        playerInfoPanel.add(currentPlayerInfoLabel);
        playerInfoPanel.add(arrowLabel);
        infoPanel.add(playerInfoPanel);

        JButton shootButton = new JButton("SHOOT");
        shootButton.addActionListener(e -> {
            shoot();
        });
        shootButton.setIcon(ImageUtil.arrow());
        infoPanel.add(shootButton);
        this.add(infoPanel);

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void initMazeView() {
        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(400, 250);
        scrollPane.setLocation(0, 50);
        this.add(scrollPane);

        gamePanel = new JPanel(new GridLayout(numOfRows, numOfCols));

        buttons = new JButton[numOfRows * numOfCols];

        for (int i = 0; i < numOfRows * numOfCols; i++) {
            int x = i / numOfCols;
            int y = i % numOfCols;
            Location currLocation = new Location(x, y);
            LocationInfo locationInfo = maze.getLocationInfo(currLocation);

            JButton currButton = new JButton();
//            currButton.setLayout(new BorderLayout());
//            currButton.setPreferredSize(new Dimension(40, 40));
            int finalI = i;
            // add action listener
            currButton.addActionListener(e -> click(finalI));
            // check if this location is visible
            if (maze.isVisible(currLocation)) {
                // render maze
                if (locationInfo.getDirections().length() == 2) {
                    currButton.setIcon(ImageUtil.hallway(locationInfo.getDirections()));
                } else {
                    currButton.setIcon(ImageUtil.room(locationInfo.getDirections()));
                }
                // render Player
                if (maze.getPlayer().getLocation().equals(currLocation)) {
                    JLabel playerLabel = new JLabel(ImageUtil.player());
                    currButton.add(playerLabel);
                }
                if (!locationInfo.getTypes().isEmpty()) {
                    if (locationInfo.getTypes().contains(CellTypeEnum.WUMPUS)) {
                        JLabel wumpusLabel = new JLabel(ImageUtil.wumpus());
                        currButton.add(wumpusLabel);
                    }
                    if (locationInfo.getTypes().contains(CellTypeEnum.BAT)) {
                        JLabel batLabel = new JLabel(ImageUtil.bat());
                        currButton.add(batLabel);
                    }
                    if (locationInfo.getTypes().contains(CellTypeEnum.PIT)) {
                        JLabel pitLabel = new JLabel(ImageUtil.pit());
                        currButton.add(pitLabel);
                    }
                }
                // get surrounding room types
                Set<CellTypeEnum> surroundingRoomTypes = maze.getSurroundingRoomTypes(currLocation);
                if (surroundingRoomTypes.contains(CellTypeEnum.WUMPUS)) {
                    JLabel wumpusNearbyLabel = new JLabel(ImageUtil.wumpusNearby());
                    currButton.add(wumpusNearbyLabel);
                }
                if (surroundingRoomTypes.contains(CellTypeEnum.PIT)) {
                    JLabel pitNearbyLabel = new JLabel(ImageUtil.pitNearby());
                    currButton.add(pitNearbyLabel);
                }
            } else {
                currButton.setIcon(ImageUtil.empty());
            }
            // store buttons for future using
            buttons[i] = currButton;
            gamePanel.add(currButton);
        }

        scrollPane.setViewportView(gamePanel);
    }

    private void refreshButtons() {
//        gamePanel.removeAll();
        for (int i = 0; i < numOfRows * numOfCols; i++) {
            int x = i / numOfCols;
            int y = i % numOfCols;
            Location currLocation = new Location(x, y);
            LocationInfo locationInfo = maze.getLocationInfo(currLocation);
            JButton currButton = buttons[i];
            currButton.removeAll();
//            currButton.setBounds(0, 0, 10, 10);
//            int finalI = i;
//            // add action listener
//            currButton.addActionListener(e -> click(finalI));
            // check if this location is visible
            if (maze.isVisible(currLocation)) {
                // render maze
                if (locationInfo.getDirections().length() == 2) {
                    currButton.setIcon(ImageUtil.hallway(locationInfo.getDirections()));
                } else {
                    currButton.setIcon(ImageUtil.room(locationInfo.getDirections()));
                }
                // render Player
                if (maze.getPlayer().getLocation().equals(currLocation)) {
                    JLabel playerLabel = new JLabel(ImageUtil.player());
                    currButton.add(playerLabel);
                }
                if (!locationInfo.getTypes().isEmpty()) {
                    if (locationInfo.getTypes().contains(CellTypeEnum.WUMPUS)) {
                        JLabel wumpusLabel = new JLabel(ImageUtil.wumpus());
                        currButton.add(wumpusLabel);
                    }
                    if (locationInfo.getTypes().contains(CellTypeEnum.BAT)) {
                        JLabel batLabel = new JLabel(ImageUtil.bat());
                        currButton.add(batLabel);
                    }
                    if (locationInfo.getTypes().contains(CellTypeEnum.PIT)) {
                        JLabel pitLabel = new JLabel(ImageUtil.pit());
                        currButton.add(pitLabel);
                    }
                }
                // get surrounding room types
                Set<CellTypeEnum> surroundingRoomTypes = maze.getSurroundingRoomTypes(currLocation);
                if (surroundingRoomTypes.contains(CellTypeEnum.WUMPUS)) {
                    JLabel wumpusNearbyLabel = new JLabel(ImageUtil.wumpusNearby());
                    currButton.add(wumpusNearbyLabel);
                }
                if (surroundingRoomTypes.contains(CellTypeEnum.PIT)) {
                    JLabel pitNearbyLabel = new JLabel(ImageUtil.pitNearby());
                    currButton.add(pitNearbyLabel);
                }
            } else {
                currButton.setIcon(ImageUtil.empty());
            }
            // store buttons for future using
//            gamePanel.add(currButton);
            currButton.revalidate();
            currButton.repaint();
        }
//        gamePanel.revalidate();
//        gamePanel.repaint();
    }

    /**
     * refresh views
     */
    private void refreshView() {
        refreshButtons();
        initInfoPanel();
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 2, 2));
        buttonPanel.setSize(150, 50);
        buttonPanel.setLocation(250, 0);
        buttonPanel.setBackground(Color.BLUE);

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(this);
        buttonPanel.add(menuButton);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        buttonPanel.add(restartButton);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        buttonPanel.add(newGameButton);

        this.add(buttonPanel);
    }

    /**
     * Global keyboard listener
     */
    private void initKeyboardListener() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        manager.addKeyEventPostProcessor(event -> {
            if (event.getID() != KeyEvent.KEY_PRESSED) {
                return false;
            }
            System.out.println(event.getKeyChar());
            switch (event.getKeyChar()) {
                case 'j':
                    shoot();
                    break;
                case 'w':
                case 'a':
                case 's':
                case 'd':
                    move(event.getKeyChar());
                    break;
            }
            return false;
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Menu":
                parent.changeView(MenuView.MENU_VIEW);
                break;
            case "Restart":
                restartGame();
                break;
            case "New Game":
                init(numOfRows, numOfCols, numOfWalls, numOfBats, numOfPits, numOfArrows);
                break;
        }
    }

    /**
     * visit a room by clicking
     *
     * @param labelIndex
     */
    private void visit(int labelIndex) {
        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
            return;
        }
        Location currLoc = new Location(labelIndex / numOfCols, labelIndex % numOfCols);
        Location playerLocation = maze.getPlayer().getLocation();
        if (currLoc.equals(playerLocation)) {
            return;
        }
        if (!maze.isAdjacent(currLoc, playerLocation)) {
            JOptionPane.showMessageDialog(this, "INVALID MOVE", "ALERT", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            maze.movePlayer(MoveEnum.parse(currLoc.getX() - playerLocation.getX(),
                    currLoc.getY() - playerLocation.getY()));
        }
        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
        }
        if (isPlayerAlive()) {
            switchPlayer();
        }
    }

    /**
     * move by keyboard
     *
     * @param c
     */
    private void move(char c) {
        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
            return;
        }
        maze.movePlayer(c);
        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
        }
        if (isPlayerAlive()) {
            switchPlayer();
        }
        refreshView();
    }

    /**
     * click to move
     *
     * @param labelIndex
     */
    private void click(int labelIndex) {
        visit(labelIndex);
        refreshView();
    }

    /**
     * restart game
     */
    private void restartGame() {
        // restart maze
        maze.restartGame();
        // restart players
        for (int i = 0; i < players.length; i++) {
            players[i] = new PlayerInfo();
            players[i].setPlayer(new Player());
            players[i].setGameStatus(AbstractMaze.ALIVE);
            players[i].getPlayer().setNumOfArrow(this.numOfArrows);
        }
        // init all players' start locations
        for (PlayerInfo player : players) {
            maze.switchPlayer(player);
            maze.setStartLocationByRoomId(maze.getStartRoomId());
        }
        maze.switchPlayer(players[currPlayerIndex]);
        refreshView();
    }

    /**
     * shoot an arrow
     */
    private void shoot() {
        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
            return;
        }
        // choose direction
        String[] directions = new String[]{"North", "South", "East", "West"};
        Object direction = JOptionPane.showInputDialog(this,
                "Shoot in which direction?",
                "SHOOT AN ARROW",
                JOptionPane.PLAIN_MESSAGE,
                ImageUtil.arrow(),
                directions,
                directions[0]);
        if (direction == null) {
            return;
        }
        // input distance
        String distance = JOptionPane.showInputDialog(this,
                "Shoot distance?",
                "SHOOT AN ARROW",
                JOptionPane.PLAIN_MESSAGE);
        if (distance == null || distance.isEmpty()) {
            return;
        }
        char dirChar;
        switch ((String) direction) {
            case "North":
                dirChar = 'w';
                break;
            case "South":
                dirChar = 's';
                break;
            case "West":
                dirChar = 'a';
                break;
            case "East":
                dirChar = 'd';
                break;
            default:
                return;
        }
        String result = maze.shoot(dirChar, Integer.parseInt(distance));
        JOptionPane.showMessageDialog(this, result);

        if (maze.getGameStatus() != AbstractMaze.ALIVE) {
            gameOver();
        }
        if (isPlayerAlive()) {
            switchPlayer();
        }
        refreshView();
    }

    private void switchPlayer() {
        // check if game's over
        if (isGameOver()) {
            return;
        }
        // check if there's alive player
        if (isPlayerAlive()) {
            int i = (currPlayerIndex + 1) % players.length;
            while (players[i].getGameStatus() != AbstractMaze.ALIVE) {
                i = (i + 1) % players.length;
            }
            currPlayerIndex = i;
            maze.switchPlayer(players[i]);
            return;
        }
        // no alive player, game over
        gameOver();
    }

    private boolean isPlayerAlive() {
        // check if there's alive player
        for (PlayerInfo playerInfo : players) {
            if (playerInfo.getGameStatus() == AbstractMaze.ALIVE) {
                return true;
            }
        }
        return false;
    }

    private boolean isGameOver() {
        // check if one of the players has won
        for (PlayerInfo playerInfo : players) {
            if (playerInfo.getGameStatus() == AbstractMaze.WIN) {
                return true;
            }
        }
        // nobody win
        // check if there's still someone alive
        for (PlayerInfo playerInfo : players) {
            if (playerInfo.getGameStatus() == AbstractMaze.ALIVE) {
                return false;
            }
        }
        // all dead
        return true;
    }
}
