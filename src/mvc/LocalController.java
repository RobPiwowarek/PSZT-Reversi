package mvc;

import game.board.Point;

public class LocalController extends GameController {

    public void move(Point point) {
        this.controller.placePawn(point);

        this.controller.putNewPawnOnBoard((int) point.getX(), (int) point.getY());

        this.controller.switchPlayers();
    }

    public LocalController(Controller controller) {
        this.controller = controller;
    }
}
