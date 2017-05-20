package mvc;

import game.board.Point;
import network.NetworkManager;

public abstract class NetworkController extends GameController {

    private NetworkManager networkManager;

    @Override
    public void makeMove(Point point) { }

    public void createNetworkManager(int port, String ip, boolean isHost) {
        networkManager = new NetworkManager(port, ip, isHost, this);
    }

    public void connect() {
        networkManager.connect();
    }

}
