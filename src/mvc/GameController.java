package mvc;

import game.board.Point;

public abstract class GameController {
    protected Controller controller;

    public abstract void move(Point p);
}
