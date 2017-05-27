package media.graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mvc.GameView;


public class OptionsPanel extends StackPane {
    private GameView gameView;

    public OptionsPanel(GameView gameView) {
        this.gameView = gameView;

        ObservableList<String> boardSize =
                FXCollections.observableArrayList(
                        "8",
                        "16",
                        "32"
                );

        final ComboBox comboBox = new ComboBox(boardSize);
        comboBox.setValue("8");

        Button button = new Button("Confirm");

        Text text = new Text("Change board size:");

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(text, comboBox);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, button);
        vBox.setAlignment(Pos.CENTER);

        getChildren().add(vBox);

        setupSizeButton(button, comboBox);
    }

    private void setupSizeButton(final Button sizeButton, final ComboBox comboBox) {
        sizeButton.setOnAction(event -> {
            Scene scene = sizeButton.getScene();
            String text = comboBox.getValue().toString();
            ;
            if (text.matches("^-?\\d+$")) {
                int size = Integer.parseInt(text);
                if (size <= 32 && size >= 8) {
                    MainMenu mainMenu = gameView.getMenu();
                    Board board = new Board(size, mainMenu.getGameView());
                    mainMenu.getGameView().setBoard(board);
                    scene.setRoot(mainMenu);
                }
            }
        });
    }


}
