package media.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OptionsPanel extends StackPane
{
    private MainMenu mainMenu;
    TextField textField;
    
    public OptionsPanel(MainMenu menu)
    {
        mainMenu=menu;
        
        textField = new TextField();
        textField.setMinSize(30, 20);
        textField.setMaxSize(30, 20);
        
        Button button = new Button("Confirm");
        setupSizeButton(button);
        
        Text text = new Text("Change board size:");
       
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(text,textField);
        hBox.setAlignment(Pos.CENTER);
        
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox,button);
        vBox.setAlignment(Pos.CENTER);
        
        getChildren().add(vBox);
    }
    
    private void setupSizeButton(final Button sizeButton)
    {
         sizeButton.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                Scene scene = sizeButton.getScene();
                String text = textField.getText();
                if (text.matches("^-?\\d+$"))
                {
                    int size = Integer.parseInt(text);
                    if (size <= 32 && size >= 8)
                    {
                        Board board = new Board(size,mainMenu.getGameView());
                        mainMenu.getGameView().setBoard(board);
                        scene.setRoot(mainMenu);
                    }
                }   
            }
        });
    }   
}
