package game;

import game.actions.Action;
import game.ai.AlphaBeta;
import game.board.GameModel;
import game.board.PawnColor;
import game.board.Point;

public class Player {

    private PawnColor color;
    private PlayerType playerType;
    private AlphaBeta ai;
    private static final int DEPTH = 64;

    public Player(PawnColor color, PlayerType playerType) {
        this.color = color;
        this.playerType = playerType;
        if (playerType == PlayerType.AI) {
            this.ai = new AlphaBeta(DEPTH);
        }
    }

    public void setGame(GameModel game) {
        if (playerType == PlayerType.AI) {
            ai.setGame(game);
        }
    }

    public Point getAIMove(long timeConstraint) {
        if (playerType == PlayerType.AI) {
            Action move = ai.play(timeConstraint);
            if(move != null) {
                return move.getPoint();
            }
        }
        return null;
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
