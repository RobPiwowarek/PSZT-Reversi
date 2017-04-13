package media.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MainMenu extends VBox
{   
    public MainMenu(int x)
    {
        super(20);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupButtons(this);
    }
            
    private void setupButtons(VBox buttonsBox)
    {
        MyButton startButton = new MyButton("Start");
        MyButton optionsButton = new MyButton("Options");
        MyButton authorButton = new MyButton("Author");
        MyButton exitButton = new MyButton("Exit");
        
        setupStartButton(startButton);
        setupOptionsButton(optionsButton);
        setupAuthorButton(authorButton);
        setupExitButton(exitButton);
        
        buttonsBox.getChildren().addAll(startButton, optionsButton, authorButton, exitButton);
    }
    
    private void setupStartButton(final Button start)
    {
         start.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                hide();
                Scene scene = start.getScene();
                Board board = new Board();
                scene.setRoot(board);
            }
        });
    }
    
    private void setupOptionsButton(Button options)
    {
        
    }
    
    private void setupAuthorButton(Button author)
    {
         author.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                JOptionPane.showMessageDialog(null, "ssss", "Author", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    private void setupExitButton(final Button exit)
    {
         exit.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                Stage stage = (Stage) exit.getScene().getWindow();
                stage.close();
            }
        });
    }
    
    public void show()
    {
        setVisible(true);
    }
    
    public void hide()
    {
        setVisible(false);
    }
}

class MyButton extends Button
{
    public MyButton(String text)
    {
        super(text);
        setMinSize(200, 30);
        setMaxSize(200, 30);
        setStyle("-fx-font: 18 arial;");
    }
}