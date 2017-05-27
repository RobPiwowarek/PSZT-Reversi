package media.graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mvc.GameView;


public class NetworkGUI extends StackPane {
    private GameView gameView;

    public NetworkGUI(GameView gameView) {
        this.gameView = gameView;

        HBox hBox[] = new HBox[6];
        VBox vBox = new VBox(10);

        for (int i = 0; i < hBox.length; i++) {
            hBox[i] = new HBox(10);
            hBox[i].setAlignment(Pos.CENTER);
        }

        Text text[] = new Text[2];
        TextField textField[] = new MyTextField[2];

        String string1[] = {"IP", "Port"};
        String string2[] = {"localhost", "4444"};

        for (int i = 0; i < text.length; i++) {
            text[i] = new Text(string1[i]);
            textField[i] = new MyTextField(string2[i]);
            hBox[i].getChildren().addAll(text[i], textField[i]);
        }

        RadioButton radioButton = new RadioButton("Host");
        hBox[2].getChildren().add(radioButton);

        createToggleGroup(hBox[3]);

        MyButton connectButton = new MyButton("Connect");
        hBox[4].getChildren().add(connectButton);
        setupConnectButton(connectButton);

        MyButton menuButton = new MyButton("MainMenu");
        hBox[5].getChildren().add(menuButton);
        setupMenuButton(menuButton);

        for (int i = 0; i < hBox.length; i++)
            vBox.getChildren().add(hBox[i]);

        vBox.setAlignment(Pos.CENTER);
        getChildren().add(vBox);
    }

    private void createToggleGroup(HBox hBox) {
        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Player mode");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("AI mode");
        button2.setToggleGroup(group);
        hBox.getChildren().addAll(button1, button2);
    }

    private void setupConnectButton(final Button conntectButton) {
        conntectButton.setOnAction(event -> {
            Scene scene = conntectButton.getScene();
            scene.setRoot(gameView.getBoard());
        });
    }

    private void setupMenuButton(final Button menuButton) {
        menuButton.setOnAction(event -> {
            Scene scene = menuButton.getScene();
            scene.setRoot(gameView.getMenu());
        });
    }

    private class MyTextField extends TextField {
        MyTextField(String string) {
            super(string);
            setMinWidth(100);
            setMaxWidth(100);
        }
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