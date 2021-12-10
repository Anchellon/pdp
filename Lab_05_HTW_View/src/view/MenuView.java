package view;

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

    // game view
    GameFrame gameFrame;

    public MenuView() {
        this.setBackground(Color.GRAY);
        setSize(400, 300);
        // use card layout
        layout = new CardLayout();
        this.setLayout(layout);
//        this.add(new JLabel("===== HUNT THE WUMPUS ====="));
        // init main menu
        this.add(getMainMenu(), "main");
        this.add(getGameSettingMenu(), "setting");
        // game frame
        gameFrame = new GameFrame();
        this.add(gameFrame, "game");
        // use main menu by default
        layout.show(this, "main");
    }

    private JPanel getMainMenu() {
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
        return mainMenuPanel;
    }

    private JPanel getGameSettingMenu() {
        JPanel gameSettingMenu = new JPanel(new GridLayout(6, 2, 1, 2));
//        gameSettingMenu.setSize(300, 300);
        // game setting
        // size
        JLabel mazeSizeLabel = new JLabel("Maze size");
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
        gameSettingMenu.add(numOfWallsLabel);
        wallsInput = new JTextField(String.valueOf(numOfWalls), 5);
        wallsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(wallsInput);
        // bats
        JLabel numOfBatsLabel = new JLabel("Number of bats");
        gameSettingMenu.add(numOfBatsLabel);
        batsInput = new JTextField(String.valueOf(numOfBats), 5);
        batsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(batsInput);
        // pits
        JLabel numOfPitsLabel = new JLabel("Number of pits");
        gameSettingMenu.add(numOfPitsLabel);
        pitsInput = new JTextField(String.valueOf(numOfPits), 5);
        pitsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(pitsInput);
        // arrows
        JLabel numOfArrowsLabel = new JLabel("Number of arrows");
        gameSettingMenu.add(numOfArrowsLabel);
        arrowsInput = new JTextField(String.valueOf(numOfArrows), 5);
        arrowsInput.setHorizontalAlignment(JTextField.CENTER);
        gameSettingMenu.add(arrowsInput);
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
        layout.show(this, "game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start Game":
                initGame();
                break;
            case "Setting":
                layout.show(this, "setting");
                break;
            case "Save and Back":
                saveSetting();
                break;
        }
    }
}
