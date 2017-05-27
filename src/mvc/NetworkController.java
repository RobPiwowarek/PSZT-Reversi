package mvc;

import game.board.Point;
import network.NetworkManager;

public class NetworkController extends GameController {

    NetworkManager networkManager;

    public NetworkController(Controller controller, NetworkManager networkManager) {
        this.controller = controller;
        this.networkManager = networkManager;
    }

    //player move
    public void move(Point point) {
        networkManager.sendMessage((int) point.getX(), (int) point.getY());

        this.controller.placePawn(point);

        this.controller.putNewPawnOnBoard((int) point.getX(), (int) point.getY());

        this.controller.switchPlayers();
    }

    public void enemyMove(Point point) {
        this.controller.placePawn(point);

        this.controller.putNewPawnOnBoard((int) point.getX(), (int) point.getY());

        this.controller.switchPlayers();
    }

}
