package media.graphics;

import mvc.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MainMenu extends VBox
{   
    private GameView gameView;
    
    public MainMenu(int x, GameView view)
    {
        super(20);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupButtons();
        gameView = view;
    }
            
    private void setupButtons()
    {
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

    private void setupPlayButton(final Button onlineButton)
    {
        onlineButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Scene scene = onlineButton.getScene();
                scene.setRoot(gameView.getPlayGUI());
            }
        });
    }

    private void setupOnlineButton(final Button onlineButton)
    {
         onlineButton.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                Scene scene = onlineButton.getScene();
                scene.setRoot(gameView.getNetworkGUI());
            }
        });
    }
    
    private void setupOptionsButton(final Button optionsButton)
    {
        final MainMenu thisMenu=this;
        optionsButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Scene scene = optionsButton.getScene();
                scene.setRoot(gameView.getOptionsPanel());
            }
        });
    }
    
    private void setupAuthorButton(Button authorButton)
    {
         authorButton.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                JOptionPane.showMessageDialog(null, "git push -fml\nschnutzer@tumblr\nlolidestroyer69", "Author", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    private void setupExitButton(final Button exitButton)
    {
         exitButton.setOnAction(new EventHandler<ActionEvent>() 
         {
            @Override
            public void handle(ActionEvent event) 
            {
                Stage stage = (Stage) exitButton.getScene().getWindow();
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
    
    public GameView getGameView()
    {
        return gameView;
    }

    private class MyButton extends Button
    {
        public MyButton(String text)
        {
            super(text);
            setMinSize(200, 40);
            setMaxSize(200, 40);
            setStyle("-fx-font: 18 arial;");
        }
    }
}

