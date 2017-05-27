package mvc;

import game.board.Point;
import game.player.HumanPlayer;

public abstract class GameController {
    Controller controller;

    public abstract void move(Point p);

    // todo: aga sprawdz to i onclick w klasie Tile, czy to ma sens? czy nie wyjdzie czasem tak ze wywolamy to gunwo jak nie mamy ludzkiego gracza?
    public boolean humanMove(Point p) {
        HumanPlayer player = (HumanPlayer) this.controller.getPlayer();
        return player.checkMove(p);
    }

    public boolean canPlace(Point point){
        return controller.canPlace(point);
    }

}
