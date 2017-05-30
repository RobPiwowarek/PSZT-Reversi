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
        if(controller.canPlace(point)) {
            networkManager.sendMessage((int) point.getX(), (int) point.getY());
            super.move(point);
        }
    }

    public void enemyMove(Point point) {
        if(controller.canPlace(point))
            super.move(point);
    }

    public void start() {} // networkmanager will do startgame

}
