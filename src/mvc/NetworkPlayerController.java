package mvc;

import game.board.Point;
import network.NetworkManager;

public class NetworkPlayerController extends NetworkController {

    private NetworkManager networkManager;

    @Override
    public void makeMove(Point point) {

    }

    public NetworkPlayerController(NetworkManager networkManager, GameView view) {
        this.view = view;
        this.networkManager = networkManager;
    }
}
