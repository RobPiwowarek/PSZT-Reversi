package game;

import game.board.PawnColor;

public class Player{

    private PawnColor color;
    private PlayerType playerType;

    public Player(PawnColor color, PlayerType playerType) {
        this.color = color;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }

    public PawnColor getColor() {
        return color;
    }
}
