package mvc;

import game.board.Point;

public abstract class GameController {
    protected Controller controller;

    public void move(Point p) {
        if(p.getX() == -1 && p.getY() == -1) {
            controller.passInModel();
            controller.passInView();
        }
        else {
            controller.placePawn(p);
            controller.putNewPawnOnBoard((int) p.getX(), (int) p.getY());
        }
        controller.switchPlayers();
    }
}
