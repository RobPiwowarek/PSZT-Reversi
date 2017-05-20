package mvc;

import game.board.GameModel;
import game.board.Point;

public abstract class GameController {
    GameModel model;
    GameView view;

    public abstract void move(Point p);

    GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }
}
