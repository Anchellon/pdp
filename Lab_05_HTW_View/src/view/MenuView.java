package view;

import consts.DifficultSettings;
import image.ImageUtil;
import util.MazeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game Menu
 *
 * @author novo
 * @since 2021/12/9
 */
public class MenuView extends JPanel implements ActionListener {
    // game setting
    private int numOfRows = 5;
    private int numOfCols = 5;
    private int numOfWalls = 7;
    private int numOfBats = 2;
    private int numOfPits = 2;
    private int numOfArrows = 5;

    private CardLayout layout;

    // setting inputs
    private JTextField rowsInput;
    private JTextField colsInput;
    private JTextField wallsInput;
    private JTextField batsInput;
    private JTextField pitsInput;
    private JTextField arrowsInput;

    JButton difficultyButton;

    // game view
    GameFrame gameFrame;

    // View names
    public static final String GAME_VIEW = "game";
    public static final String MENU_VIEW = "main";
    public static final String SETTING_VIEW = "setting";

    public MenuView() {
        this.setBackground(Color.GRAY);
        setSize(400, 300);
        // use card layout
        layout = new CardLayout();
        this.setLayout(layout);
//        this.add(new JLabel("===== HUNT THE WUMPUS ====="));
        // init main menu
        this.add(initMainMenu(), MENU_VIEW);
        this.add(initGameSettingMenu(), SETTING_VIEW);
        // game frame
        gameFrame = new GameFrame(this);
        this.add(gameFrame, GAME_VIEW);
        // use main menu by default
        layout.show(this, MENU_VIEW);
    }

    private JPanel initMainMenu() {
        JPanel mainMenuPanel = new JPanel();
        // start game button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setSize(30, 20);
        // action
        startGameButton.addActionListener(this);
        // game setting menu
        JButton gameSettingButton = new JButton("Setting");
        gameSettingButton.setSize(30, 20);
        // action
        gameSettingButton.addActionListener(this);

        mainMenuPanel.add(startGameButton);
        mainMenuPanel.add(gameSettingButton);

        // game intro
        JPanel introPanel = new JPanel(new GridLayout(4, 1));
        JLabel targetLabel = new JLabel(ImageUtil.target());
        introPanel.add(targetLabel);
        JLabel moveIntroLabel = new JLabel("move: click adjacent cells or press [w, a, s, d]");
        moveIntroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introPanel.add(moveIntroLabel);

        JLabel wumpusLabel = new JLabel(ImageUtil.wumpus());
        introPanel.add(wumpusLabel);
        JLabel shootIntroLabel = new JLabel("shoot: click shoot button or press [j]");
        shootIntroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introPanel.add(shootIntroLabel);

        mainMenuPanel.add(introPanel);

        return mainMenuPanel;
    }

    private JPanel initGameSettingMenu() {
        JPanel gameSettingMenu = new JPanel(new GridLayout(6, 2, 1, 2));
//        gameSettingMenu.setSize(300, 300);
        // game setting
        // size
        JLabel mazeSizeLabel = new JLabel("Maze size");
        mazeSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingMenu.add(mazeSizeLabel);
        JPanel sizePanel = new JPanel();
        // rows
        rowsInput = new JTextField(String.valueOf(numOfRows), 4);
        rowsInput.setHorizontalAlignment(JTextField.CENTER);
        sizePanel.add(rowsInput);
        // *
        JLabel multiSymbol = new JLabel("*");
        sizePanel.add(multiSymbol, BorderLayout.CENTER);
        // cols
        colsInput = new JTextField(String.valueOf(numOfCols), 4);
        colsInput.setHorizontalAlignment(JTextField.CENTER);
        sizePanel.add(colsInput);
        gameSettingMenu.add(sizePanel);
        // walls
        JLabel numOfWallsLabel = new JLabel("Number of walls");
        numOfWallsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingMenu.add(numOfWallsLabel);
        wallsInput = new JTextField(String.valueOf(numOfWalls), 5);
        wallsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(wallsInput);
        // bats
        JLabel numOfBatsLabel = new JLabel("Number of bats");
        numOfBatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingMenu.add(numOfBatsLabel);
        batsInput = new JTextField(String.valueOf(numOfBats), 5);
        batsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(batsInput);
        // pits
        JLabel numOfPitsLabel = new JLabel("Number of pits");
        numOfPitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingMenu.add(numOfPitsLabel);
        pitsInput = new JTextField(String.valueOf(numOfPits), 5);
        pitsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(pitsInput);
        // arrows
        JLabel numOfArrowsLabel = new JLabel("Number of arrows");
        numOfArrowsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingMenu.add(numOfArrowsLabel);
        arrowsInput = new JTextField(String.valueOf(numOfArrows), 5);
        arrowsInput.setHorizontalAlignment(JTextField.CENTER);

        gameSettingMenu.add(arrowsInput);

        // difficulty button
        difficultyButton = new JButton();
        difficultyButton.setText("MEDIUM");
        difficultyButton.addActionListener(e -> changePresetDifficulty());
        gameSettingMenu.add(difficultyButton);
        // save button
        JButton saveButton = new JButton("Save and Back");
        saveButton.setSize(30, 20);
        gameSettingMenu.add(saveButton);
        saveButton.addActionListener(this);
        return gameSettingMenu;
    }

    /**
     * Try to save new setting
     */
    private void saveSetting() {
        // get new data
        int newRows = Integer.parseInt(rowsInput.getText().trim());
        int newCols = Integer.parseInt(colsInput.getText().trim());
        int newWalls = Integer.parseInt(wallsInput.getText().trim());
        int newBats = Integer.parseInt(batsInput.getText().trim());
        int newPits = Integer.parseInt(pitsInput.getText().trim());
        int newArrows = Integer.parseInt(arrowsInput.getText().trim());
        if (!MazeUtil.isValidMaze(newRows, newCols, newWalls, newPits, newBats) || newArrows <= 0) {
            // invalid setting
            JOptionPane.showMessageDialog(this, "INVALID SETTING! PLEASE TRY AGAIN", "ALERT", JOptionPane.ERROR_MESSAGE);
        } else {
            // valid setting
            this.numOfRows = newRows;
            this.numOfCols = newCols;
            this.numOfWalls = newWalls;
            this.numOfBats = newBats;
            this.numOfPits = newPits;
            this.numOfArrows = newArrows;
            layout.show(this, "main");
        }

    }

    /**
     * initialize game
     */
    private void initGame() {
        // create game frame
        gameFrame.init(numOfRows,
                numOfCols,
                numOfWalls,
                numOfBats,
                numOfPits,
                numOfArrows);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start Game":
                initGame();

                break;
            case "Setting":
                layout.show(this, SETTING_VIEW);
                break;
            case "Save and Back":
                saveSetting();
                break;
        }
    }

    /**
     * 0: easy
     * 1: medium
     * 2: hard
     */
    private static int currDifficult = 1;

    private void changePresetDifficulty() {
        currDifficult = (currDifficult + 1) % 3;
        if (currDifficult == 0) {
            // easy mode
            rowsInput.setText(DifficultSettings.EASY_ROWS);
            colsInput.setText(DifficultSettings.EASY_COLS);
            wallsInput.setText(DifficultSettings.EASY_WALLS);
            batsInput.setText(DifficultSettings.EASY_BATS);
            pitsInput.setText(DifficultSettings.EASY_PITS);
            arrowsInput.setText(DifficultSettings.EASY_ARROWS);
            // change button
            difficultyButton.setText("EASY");
        } else if (currDifficult == 1) {
            // medium mode
            rowsInput.setText(DifficultSettings.MEDIUM_ROWS);
            colsInput.setText(DifficultSettings.MEDIUM_COLS);
            wallsInput.setText(DifficultSettings.MEDIUM_WALLS);
            batsInput.setText(DifficultSettings.MEDIUM_BATS);
            pitsInput.setText(DifficultSettings.MEDIUM_PITS);
            arrowsInput.setText(DifficultSettings.MEDIUM_ARROWS);
            // change button
            difficultyButton.setText("MEDIUM");
        } else {
            // hard mode
            rowsInput.setText(DifficultSettings.HARD_ROWS);
            colsInput.setText(DifficultSettings.HARD_COLS);
            wallsInput.setText(DifficultSettings.HARD_WALLS);
            pitsInput.setText(DifficultSettings.HARD_BATS);
            batsInput.setText(DifficultSettings.HARD_PITS);
            arrowsInput.setText(DifficultSettings.HARD_ARROWS);
            // change button
            difficultyButton.setText("HARD");
        }
        difficultyButton.revalidate();
        difficultyButton.repaint();
    }

    public void changeView(String view) {
        layout.show(this, view);
    }
}
