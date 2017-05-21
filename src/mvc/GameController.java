package mvc;

import game.board.GameModel;
import game.board.Point;
import game.player.HumanPlayer;

public abstract class GameController {
    GameModel model;
    GameView view;

    public abstract void move(Point p);

    // todo: aga sprawdz to i onclick w klasie Tile, czy to ma sens? czy nie wyjdzie czasem tak ze wywolamy to gunwo jak nie mamy ludzkiego gracza?
    public boolean humanMove(Point p){
        HumanPlayer player = (HumanPlayer)this.model.getPlayer();
        return player.checkMove(p);
    }

    public GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }
}
