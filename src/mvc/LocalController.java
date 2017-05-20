package mvc;

import game.board.GameModel;
import game.board.Point;

public class LocalController extends GameController {

    public void move(Point point){
        this.model.placePawn(point);

        this.view.getBoard().putNewPawn((int) point.getX(), (int)point.getY());

        this.model.switchPlayers();
    }

    public LocalController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }
}
