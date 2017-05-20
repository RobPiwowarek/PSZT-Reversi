package media.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;

import javafx.scene.text.Text;


public class NetworkGUI extends StackPane
{
    private MainMenu mainMenu;

    public NetworkGUI(MainMenu menu) {
        mainMenu = menu;

        HBox hBox[] = new HBox[5];
        VBox vBox = new VBox(10);

        for (int i = 0; i < hBox.length; i++)
        {
            hBox[i] = new HBox(10);
            hBox[i].setAlignment(Pos.CENTER);
        }

        Text text[] = new Text[2];
        TextField textField[] = new MyTextField[2];

        String string1[] = {"IP","Port"};
        String string2[] = {"localhost","4444"};

        for (int i = 0; i < text.length; i++)
        {
            text[i] = new Text(string1[i]);
            textField[i] = new MyTextField(string2[i]);
            hBox[i].getChildren().addAll(text[i], textField[i]);
        }

        RadioButton radioButton = new RadioButton("Host");
        hBox[2].getChildren().add(radioButton);

        createToggleGroup(hBox[3]);

        Button connectButton = new Button("Connect");
        hBox[4].getChildren().add(connectButton);

        for (int i = 0; i < hBox.length; i++)
            vBox.getChildren().add(hBox[i]);

        vBox.setAlignment(Pos.CENTER);
        getChildren().add(vBox);

        setupConnect(connectButton);
    }

    private void createToggleGroup(HBox hBox)
    {
        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Player mode");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("AI mode");
        button2.setToggleGroup(group);
        hBox.getChildren().addAll(button1,button2);
    }

    private void setupConnect(final Button conntectButton)
    {
        conntectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Scene scene = conntectButton.getScene();
                scene.setRoot(mainMenu.getGameView().getBoard());
            }
        });
    }
}

class MyTextField extends TextField
{
    MyTextField(String string)
    {
        super(string);
        setMinWidth(100);
        setMaxWidth(100);
    }
}
