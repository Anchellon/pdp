package view;

/**
 * game control interface
 *
 * @author novo
 * @since 2021/12/14
 */
public interface GameControl {
    void backToMenu();

    void restartGame();

    void init();

    void shoot();

    void move(char c);
}
