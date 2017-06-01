package media.graphics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.GameView;

import javax.swing.*;

public class MainMenu extends VBox {
    private GameView gameView;

    public MainMenu(int x, GameView view) {
        super(20);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupButtons();
        gameView = view;
    }

    private void setupButtons() {
        MyButton playButton = new MyButton("Play");
        MyButton onlineButton = new MyButton("Network Play");
        MyButton optionsButton = new MyButton("Options");
        MyButton authorButton = new MyButton("Author");
        MyButton exitButton = new MyButton("Exit");

        setupPlayButton(playButton);
        setupOnlineButton(onlineButton);
        setupOptionsButton(optionsButton);
        setupAuthorButton(authorButton);
        setupExitButton(exitButton);

        getChildren().addAll(playButton, onlineButton, optionsButton, authorButton, exitButton);
    }

    private void setupPlayButton(final Button onlineButton) {
        onlineButton.setOnAction(event -> {
            Scene scene = onlineButton.getScene();
            scene.setRoot(gameView.getPlayGUI());
        });
    }

    private void setupOnlineButton(final Button onlineButton) {
        onlineButton.setOnAction(event -> {
            Scene scene = onlineButton.getScene();
            scene.setRoot(gameView.getNetworkGUI());
        });
    }

    private void setupOptionsButton(final Button optionsButton) {
        final MainMenu thisMenu = this;
        optionsButton.setOnAction(event -> {
            Scene scene = optionsButton.getScene();
            scene.setRoot(gameView.getOptionsPanel());
        });
    }

    private void setupAuthorButton(Button authorButton) {
        authorButton.setOnAction(event -> JOptionPane.showMessageDialog(null, "Robert Piwowarek\nAgata Kłoss\nMarcin Kondras\nWEITI PW 2017", "Author", JOptionPane.INFORMATION_MESSAGE));
    }

    private void setupExitButton(final Button exitButton) {
        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    GameView getGameView() {
        return gameView;
    }

    private class MyButton extends Button {
        MyButton(String text) {
            super(text);
            setMinSize(200, 40);
            setMaxSize(200, 40);
            setStyle("-fx-font: 18 arial;");
        }
    }
}

