package game;

import game.board.PawnColor;

public enum PlayerType {
    HUMAN(PawnColor.EMPTY),
    AI(PawnColor.EMPTY),
    NETWORK(PawnColor.EMPTY);

    private PawnColor color;

    PlayerType(PawnColor color){
        this.color = color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }

    public PawnColor getColor() {
        return color;
    }
}
