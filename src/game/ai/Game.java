package game.ai;

import game.actions.Action;

import java.util.Collection;

public interface Game {
    // TODO - bardziej optymalniejsiejsze cos niz klonowanie calej gry
    Game clone();

    void makeMove(Action move);

    Collection<Action> getPossibleMoves();

    int getScoring(); // jak dobry jest board dla obecnego gracza

    boolean isOver();
}
