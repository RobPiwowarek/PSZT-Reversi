package game.ai;

import game.actions.Action;

public interface Game {
    // TODO - bardziej optymalniejsiejsze cos niz klonowanie calej gry
    Game clone();

    void makeMove(Action move);

    Action[] getPossibleMoves();

    int getScoring(); // jak dobry jest board dla obecnego gracza

    boolean isOver();
}
