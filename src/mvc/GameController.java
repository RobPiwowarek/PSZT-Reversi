package mvc;

import game.board.Point;

public abstract class GameController {

    //TODO: gamemodel?
    GameView view;

    public abstract void makeMove(Point point);
}
