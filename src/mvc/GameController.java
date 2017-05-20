package mvc;

import game.board.GameModel;
import game.board.Point;
import game.player.HumanPlayer;

public abstract class GameController {
    GameModel model;
    GameView view;

    public abstract void move(Point p);

    protected void humanMove(Point p){
        HumanPlayer player = (HumanPlayer)this.model.getPlayer();
        player.checkMove(p);
    }

    public GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }
}
