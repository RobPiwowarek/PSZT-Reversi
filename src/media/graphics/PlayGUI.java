package media.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import mvc.GameView;

public class PlayGUI extends StackPane
{
    private GameView gameView;

    public PlayGUI(GameView gameView)
    {
        this.gameView = gameView;

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play");
        setupPlayButton(playButton);

        createToggleGroup(hBox);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBox,playButton);
        vBox.setAlignment(Pos.CENTER);

        getChildren().add(vBox);
    }

    private void createToggleGroup(HBox hBox)
    {
        ToggleGroup group = new ToggleGroup();

        RadioButton button1 = new RadioButton("Player vs. AI");
        button1.setToggleGroup(group);
        button1.setSelected(true);

        RadioButton button2 = new RadioButton("Player vs. Player");
        button2.setToggleGroup(group);

        RadioButton button3 = new RadioButton("AI vs. AI");
        button3.setToggleGroup(group);

        hBox.getChildren().addAll(button1,button2,button3);
    }

    private void setupPlayButton(final Button playButton)
    {
        playButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Scene scene = playButton.getScene();
                scene.setRoot(gameView.getBoard());
            }
        });
    }
}