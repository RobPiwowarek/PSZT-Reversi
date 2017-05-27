import game.board.GameModel;
import javafx.application.Application;
import javafx.stage.Stage;
import mvc.Controller;
import mvc.GameView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        restart(primaryStage);
    }

    // TODO:

    public void restart(Stage primaryStage){
       // GameModel gameModel = new GameModel()
        GameView gameView = new GameView(primaryStage);
       // Controller controller = new Controller(gameModel, gameView);
        // gameView.setGameController(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
