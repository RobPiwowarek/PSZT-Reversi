package media.graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.*;
import mvc.GameView;

public class PlayGUI extends StackPane {
    private GameView gameView;

    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private RadioButton button5;

    public PlayGUI(GameView gameView) {
        this.gameView = gameView;

        HBox hBox[] = new HBox[2];

        for (int i = 0; i < hBox.length; i++) {
            hBox[i] = new HBox(10);
            hBox[i].setAlignment(Pos.CENTER);
        }

        MyButton playButton = new MyButton("Play");
        setupPlayButton(playButton);

        MyButton menuButton = new MyButton("MainMenu");
        setupMenuButton(menuButton);

        createToggleGroup(hBox[0]);
        createToggleGroup2(hBox[1]);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBox[0], hBox[1], playButton, menuButton);
        vBox.setAlignment(Pos.CENTER);

        setupRadioButton(button1, hBox[1]);

        getChildren().add(vBox);
    }

    private void createToggleGroup(HBox hBox) {
        ToggleGroup group = new ToggleGroup();

        button1 = new RadioButton("Player vs. AI");
        button1.setToggleGroup(group);
        button1.setSelected(true);

        button2 = new RadioButton("Player vs. Player");
        button2.setToggleGroup(group);

        button3 = new RadioButton("AI vs. AI");
        button3.setToggleGroup(group);

        hBox.getChildren().addAll(button1, button2, button3);
    }

    private void createToggleGroup2(HBox hBox)
    {
        ToggleGroup group = new ToggleGroup();

        button4 = new RadioButton("Player begins");
        button4.setToggleGroup(group);

        button5 = new RadioButton("AI begins");
        button5.setToggleGroup(group);
        hBox.getChildren().addAll(button4, button5);
    }

    private void setupPlayButton(final Button playButton) {
        playButton.setOnAction(event -> {
            Scene scene = playButton.getScene();
            scene.setRoot(gameView.getBoard());

            if (button1.isSelected()) {
                this.gameView.getGameController().createHumanVsAILocalController();
            } else if (button2.isSelected()) {
                this.gameView.getGameController().createHumanVsHumanLocalController();
            } else {
                this.gameView.getGameController().createAIvsAILocalController();
            }
            this.gameView.startGame();

        });
    }

    private void setupMenuButton(final Button menuButton) {
        menuButton.setOnAction(event -> {
            Scene scene = menuButton.getScene();
            scene.setRoot(gameView.getMenu());
        });
    }

    private void setupRadioButton(final RadioButton radioButton, HBox hBox)
    {
        radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    hBox.setVisible(true);
                } else {
                    hBox.setVisible(false);
                }
            }
        });
    }

    private class MyButton extends Button {
        public MyButton(String text) {
            super(text);
            setMinSize(100, 40);
            setMaxSize(100, 40);
            setStyle("-fx-font: 12 arial;");
        }
    }
}