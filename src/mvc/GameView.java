/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import game.player.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import media.graphics.*;

public class GameView extends Application 
{
    private MainMenu mainMenu;
    private Board board;
    private GameController gameController;
    OptionsPanel optionsPanel;
    NetworkGUI networkGUI;
    PlayGUI playGUI;

    @Override
    public void start(Stage primaryStage) 
    {
        mainMenu = new MainMenu(20,this);
        board = new Board(8,this);
        optionsPanel = new OptionsPanel(this);
        networkGUI  = new NetworkGUI(this);
        playGUI = new PlayGUI(this);
        
        Scene scene = new Scene(mainMenu, 700, 700, Color.WHITESMOKE);
 
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
    
    public MainMenu getMenu() 
    {
        return mainMenu;
    }

    public void setMenu(MainMenu menu) 
    {
        this.mainMenu = menu;
    }
    
    public Board getBoard() 
    {
        return board;
    }

    public void setBoard(Board board) 
    {
        this.board = board;
    }

    public OptionsPanel getOptionsPanel() { return optionsPanel; }

    public void setOptionPanel(OptionsPanel optionsPanel) { this.optionsPanel = optionsPanel; }

    public NetworkGUI getNetworkGUI() { return networkGUI; }

    public void setNetworkGUI(NetworkGUI networkGUI)
    {
        this.networkGUI = networkGUI;
    }

    public PlayGUI getPlayGUI() { return playGUI; }

    public void setPlayGUI(PlayGUI playGUI)
    {
        this.playGUI = playGUI;
    }
}
