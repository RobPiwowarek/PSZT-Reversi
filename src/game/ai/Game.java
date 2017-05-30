package game.ai;

import game.actions.Action;

import java.util.Collection;

public interface Game {
    Game clone();

    void makeMove(Action move);

    void undoLastMove();

    Collection<Action> getPossibleMoves();

    boolean isOver();

    int getBlackPawnCount();

    int getWhitePawnCount();

    int getCurrentColorAsInt();

    int getPawnAsInt(int x, int y); // empty = 0, black = 1, white = -1

    short getSize();

    long getZobristKey();

    int getNPossibleMoves(int color);

}
