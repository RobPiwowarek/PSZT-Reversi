package mvc;

import game.Player;
import game.PlayerType;
import game.board.GameModel;
import game.board.PawnColor;
import game.board.Point;
import network.NetworkManager;


public class Controller {
    private GameView gameView;
    private GameModel gameModel;
    private NetworkManager networkManager;
    private static final int AI_TIME_CONSTRAINT = 1000000000;
    private GameController gameController;

    public Controller(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void placePawn(Point point) {
        this.gameModel.placePawn(point);
    }

    public void putNewPawnOnBoard(int x, int y) {
        this.gameView.putNewPawn(x, y);
    }

    public void switchPlayers() {
        this.gameModel.switchPlayers();
        Player currentPlayer = gameModel.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.AI) {
            move(currentPlayer.getAIMove(AI_TIME_CONSTRAINT));
        }
    }

    public boolean isCurrentPlayerHuman() {
        return gameModel.getCurrentPlayer().getPlayerType() == PlayerType.HUMAN;
    }

    public void move(Point point) {
        gameController.move(point);
    }

    public boolean canPlace(Point point) {
        return gameModel.canPlace(point);
    }

    public Player getCurrentPlayer() {
        return gameModel.getCurrentPlayer();
    }

    public PawnColor getCurrentPlayerColor() {
        return gameModel.getCurrentPlayer().getColor();
    }

    public void createNetworkManager(int port, String host, boolean isServer) {
        networkManager = new NetworkManager(port, host, isServer);
    }

    public void createHumanVsHumanNetworkController(boolean isRemotePlayerHost, PlayerType HumanOrAI, int port, String host) {
        createNetworkManager(port, host, !isRemotePlayerHost);
        NetworkController networkController = new NetworkController(this, networkManager);
        networkManager.setGameController(networkController);

        Player player1, player2;

        if (isRemotePlayerHost) {
            player1 = new Player(PawnColor.LIGHT, HumanOrAI);
            player1.setGame(gameModel);
            player2 = new Player(PawnColor.DARK, PlayerType.NETWORK);
            gameModel.setPlayers(player2, player1);
        } else {
            player2 = new Player(PawnColor.LIGHT, PlayerType.NETWORK);
            player1 = new Player(PawnColor.DARK, HumanOrAI);
            player2.setGame(gameModel);
            gameModel.setPlayers(player1, player2);
        }

        gameController = networkController;
    }

    public void createHumanVsHumanLocalController() {
        LocalController localController = new LocalController(this);
        Player player1 = new Player(PawnColor.DARK, PlayerType.HUMAN);
        Player player2 = new Player(PawnColor.LIGHT, PlayerType.HUMAN);

        gameModel.setPlayers(player1, player2);

        gameController = localController;
    }

    // TODO: jakas mozliwosc decyzji kto zaczyna?
    public void createHumanVsAILocalController() {
        LocalController localController = new LocalController(this);

        Player player1 = new Player(PawnColor.DARK, PlayerType.HUMAN);
        Player player2 = new Player(PawnColor.LIGHT, PlayerType.AI);
        player2.setGame(gameModel);

        gameModel.setPlayers(player1, player2);

        gameController = localController;

    }

    public void createAIvsAILocalController() {
        LocalController localController = new LocalController(this);

        Player player1 = new Player(PawnColor.DARK, PlayerType.AI);
        Player player2 = new Player(PawnColor.LIGHT, PlayerType.AI);
        player1.setGame(gameModel);
        player2.setGame(gameModel);

        gameModel.setPlayers(player1, player2);

        gameController = localController;

    }
}
