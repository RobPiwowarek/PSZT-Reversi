import game.board.GameModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.Controller;
import mvc.GameView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        restart(primaryStage);
    }

    // TODO:

    public void restart(Stage primaryStage) {
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(primaryStage);
        Controller controller = new Controller(gameView, gameModel);
        gameView.setGameController(controller);
        primaryStage.setOnCloseRequest(we -> controller.killThreads());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
