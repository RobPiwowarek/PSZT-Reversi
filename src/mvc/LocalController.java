package mvc;

import game.PlayerType;
import game.board.Point;

public class LocalController extends GameController {

    public void move(Point point) {
        /*
        switch (player) {
            case HUMAN:
                controller.placePawn(point);
                controller.putNewPawnOnBoard((int) point.getX(), (int) point.getY());
                controller.switchPlayers();
                break;
            case AI:
                // TODO;
                break;
            default:
                // todo;
                break;
        }
        */

    }

    public LocalController(Controller controller) {
        this.controller = controller;
    }
}
