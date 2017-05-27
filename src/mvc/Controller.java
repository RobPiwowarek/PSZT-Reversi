package mvc;

import game.board.GameModel;
import game.board.Point;
import game.player.AIPlayer;
import game.player.HumanPlayer;
import game.player.Player;

public class Controller {
    private GameView gameView;
    private GameModel gameModel;

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

    public Player getPlayer() {
        return this.gameModel.getPlayer();
    }

    public void placePawn(Point point) {
        this.gameModel.placePawn(point);
    }

    public void putNewPawnOnBoard(int x, int y) {
        this.gameView.putNewPawn(x, y);
    }

    public void switchPlayers() {
        this.gameModel.switchPlayers();
    }

    public boolean canPlace(Point point){
        return gameModel.canPlace(point);
    }

    public boolean humanMove(Point locationOnBoard){
        return gameController.humanMove(locationOnBoard);
    }

    public void createHumanVsHumanLocalController() {
        gameController = new LocalController(this);
        Player player1 = new HumanPlayer();
        Player player2 = new HumanPlayer();
        gameModel.setPlayer(player1);
        gameModel.setOpponent(player2);
    }

    public void createHumanVsAILocalController() {
        gameController = new LocalController(this);
        Player player1 = new HumanPlayer();
        Player player2 = new AIPlayer();
        gameModel.setPlayer(player1);
        gameModel.setOpponent(player2);
    }

    public void createAIvsAILocalController() {
        gameController = new LocalController(this);
        Player player1 = new AIPlayer();
        Player player2 = new AIPlayer();
        gameModel.setPlayer(player1);
        gameModel.setOpponent(player2);
    }
}
