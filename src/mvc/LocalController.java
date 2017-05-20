package mvc;

import game.board.GameModel;
import game.board.Point;

public class LocalController extends GameController {

    public void move(Point p){
        //TODO: graphics

        this.model.switchPlayers();
    }

    public LocalController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }
}
