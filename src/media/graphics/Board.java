package media.graphics;

// TODO: size ma sie zmieniac od 8x8 do 32x32

import mvc.GameView;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class Board extends StackPane
{
    private final GameView gameView;
    
    private final Tile[][] tiles;
   
    private final int size;
    int round;
    
    public Board(int s, GameView view)
    {
        gameView = view;
        round=0;
        size=s;
        tiles = new Tile[size][size];
        setupEmptyTiles();
        startingPosition();
    }
    
    private void setupEmptyTiles()
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        HBox hBoxes[] = new HBox[size];
        for(int i=0;i<hBoxes.length;i++)
        {
            hBoxes[i]=new HBox();
            hBoxes[i].setAlignment(Pos.CENTER);
            for(int j=0;j<tiles.length;j++)
            {
                tiles[i][j]=new Tile(this);
                hBoxes[i].getChildren().add(tiles[i][j]);
            }
        }
        vBox.getChildren().addAll(hBoxes);
        getChildren().add(vBox);
    }
    
    private void startingPosition()
    {
        int a = size/2;
        int b=a-1;
        tiles[a][a].putNewWhitePawn();
        tiles[b][b].putNewWhitePawn();
        tiles[a][b].putNewBlackPawn();
        tiles[b][a].putNewBlackPawn();
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
