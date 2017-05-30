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
    private NetworkManager networkManager = null;
    private static final long AI_TIME_CONSTRAINT = 1000;
    private GameController gameController;
    private Runnable aiMove;
    private Thread aiThread;

    public Controller(GameView gameView, GameModel gameModel) {
        aiMove = () -> move(getCurrentPlayer().getAIMove(AI_TIME_CONSTRAINT));
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.gameModel.setSize((short)8);
        this.gameModel.setController(this);
    }

    public GameController getGameController() {
        return gameController;
    }

    private void killAIThread() {
        if(aiThread != null) {
            aiThread.interrupt();
        }
    }

    public void killThreads()
    {
        killAIThread();
        if (networkManager != null)
            networkManager.closeSockets();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameModelSize(short size){
        gameModel.setSize(size);
        this.gameModel.setController(this);
    }

    public void placePawn(Point point) {
        this.gameModel.placePawn(point);
    }
    public void passInModel() { this.gameModel.pass(); }

    public void putNewPawnOnBoard(int x, int y) {
        this.gameView.putNewPawn(x, y);
    }
    public void passInView() { this.gameView.registerPass(); }

    public void flipPawn(int x, int y) {
        this.gameView.flipPawn(x,y);
    }

    private void makeAIMove() {
        aiThread = new Thread(aiMove);
        aiThread.start();
    }
    public void startGame() {
        Player currentPlayer = gameModel.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.AI) {
            makeAIMove();
        }
    }

    public void start() {
        gameController.start();
    }

    public void switchPlayers() {
        this.gameModel.switchPlayers();
        Player currentPlayer = gameModel.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.AI && !isGameOver()) {
            makeAIMove();
        }
    }

    public void endGame() {
        gameModel.reset();
        killThreads();
    }

    public void showGameOver() { gameView.showGameOver(); }

    public boolean isCurrentPlayerHuman() {
        return gameModel.getCurrentPlayer().getPlayerType() == PlayerType.HUMAN;
    }
    public boolean isGameOver() { return gameModel.isOver(); }
    public void move(Point point) { gameController.move(point); }
    public void pass() { move(new Point(-1,-1)); }

    public boolean canPlace(Point point) {
        if(point.getX() == -1 && point.getY() == -1) {
            return gameModel.canPass();
        }
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
        networkManager.setController(this);
        networkManager.connect();

        Player player1, player2;

        if (isRemotePlayerHost) {
            player1 = new Player(PawnColor.LIGHT, HumanOrAI);
            player1.setGame(gameModel);
            player2 = new Player(PawnColor.DARK, PlayerType.NETWORK);
            gameModel.setPlayers(player2, player1);
            gameView.changeTextColor(1);
        } else {
            player2 = new Player(PawnColor.LIGHT, PlayerType.NETWORK);
            player1 = new Player(PawnColor.DARK, HumanOrAI);
            player1.setGame(gameModel);
            gameModel.setPlayers(player1, player2);
            gameView.changeTextColor(0);
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

    public void createHumanVsAILocalController(boolean humanStarts) {
        LocalController localController = new LocalController(this);
        Player player1, player2;

        if (humanStarts) {
            player1 = new Player(PawnColor.DARK, PlayerType.HUMAN);
            player2 = new Player(PawnColor.LIGHT, PlayerType.AI);
            player2.setGame(gameModel);
            gameView.changeTextColor(0);
        }
        else {
            player1 = new Player(PawnColor.DARK, PlayerType.AI);
            player1.setGame(gameModel);
            player2 = new Player(PawnColor.LIGHT, PlayerType.HUMAN);
            gameView.changeTextColor(1);
        }

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

    public void setInfo(String info)
    {
        gameView.getBoard().setTurnText(info);
    }

    public void disableBoard() { gameView.getBoard().disable();}

    public void enableBoard() { gameView.getBoard().enable();}

    public String getScore()
    {
        return gameModel.getBlackPawnCount() + ":" + gameModel.getWhitePawnCount();
    }
}
