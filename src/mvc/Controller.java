package mvc;

import game.board.GameModel;
import game.board.Point;
import game.PlayerType;

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

    public void placePawn(Point point) {
        this.gameModel.placePawn(point);
    }

    public void putNewPawnOnBoard(int x, int y) {
        this.gameView.putNewPawn(x, y);
    }

    public void switchPlayers() {
        this.gameModel.switchPlayers();
    }

    public boolean isCurrentPlayerHuman(){
       // return gameModel.getCurrentPlayer() == gameController.getColor();
    }

    public void move(Point point) {
        gameController.move(point);
    }

    public boolean canPlace(Point point){
        return gameModel.canPlace(point);
    }

    public void createHumanVsHumanLocalController() {
        LocalController localController = new LocalController(this);

        gameController = localController;
    }

    public void createHumanVsAILocalController() {
        LocalController localController = new LocalController(this);

        gameController = localController;

    }

    public void createAIvsAILocalController() {
        LocalController localController = new LocalController(this);

        gameController = localController;

    }
}
