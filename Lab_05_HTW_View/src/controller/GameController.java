package controller;

import view.GameControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author novo
 * @since 2021/12/14
 */
public class GameController implements ActionListener {

    private GameControl gameFrame;

    public GameController(GameControl gameFrame) {
        this.gameFrame = gameFrame;
        initKeyboardListener();
    }

    /**
     * action listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Menu":
                gameFrame.backToMenu();
                break;
            case "Restart":
                gameFrame.restartGame();
                break;
            case "New Game":
                gameFrame.init();
                break;
            case "SHOOT":
                gameFrame.shoot();
        }
    }

    /**
     * Global keyboard listener
     */
    private void initKeyboardListener() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        manager.addKeyEventPostProcessor(event -> {
            // avoid multi pressing
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                System.out.println(event.getKeyChar());
                switch (event.getKeyChar()) {
                    case 'j':
                        gameFrame.shoot();
                        break;
                    case 'w':
                    case 'a':
                    case 's':
                    case 'd':
                        gameFrame.move(event.getKeyChar());
                        break;
                }
            }
            return false;
        });
    }
}
