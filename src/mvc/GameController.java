package mvc;

import game.board.Point;

public abstract class GameController {
    protected Controller controller;

    public void move(Point p) {
        controller.placePawn(p);
        controller.putNewPawnOnBoard((int) p.getX(), (int) p.getY());
        controller.switchPlayers();
    }
}
