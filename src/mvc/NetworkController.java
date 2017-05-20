package mvc;

import game.actions.Place;
import game.board.GameModel;
import game.board.Point;
import game.player.HumanPlayer;
import network.NetworkManager;

public class NetworkController extends GameController {

    NetworkManager networkManager;

    public NetworkController(GameView view, GameModel model, NetworkManager networkManager) {
        this.view = view;
        this.model = model;
        this.networkManager = networkManager;
    }

    //player move
    public void move(Point point) {
        networkManager.sendMessage((int)point.getX(), (int)point.getY());

        this.model.placePawn(point);

        this.view.getBoard().putNewPawn((int) point.getX(), (int)point.getY());

        this.model.switchPlayers();
    }

    public void enemyMove(Point point){
        this.model.placePawn(point);

        this.view.getBoard().putNewPawn((int) point.getX(), (int)point.getY());

        this.model.switchPlayers();
    }

}
