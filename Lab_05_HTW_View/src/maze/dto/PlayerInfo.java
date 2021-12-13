package maze.dto;

import player.Player;

/**
 * @author novo
 * @since 2021/12/12
 */
public class PlayerInfo {
    private Player player;
    private int gameStatus;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }
}
