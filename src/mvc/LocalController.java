package mvc;

import game.board.Point;

public class LocalController extends GameController {

    public LocalController(Controller controller) {
        this.controller = controller;
    }
}
