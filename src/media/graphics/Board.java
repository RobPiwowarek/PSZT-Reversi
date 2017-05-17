package media.graphics;

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
        setScale();
    }

    private void setupEmptyTiles()
    {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBoxes[] = new VBox[size];

        for(int i=0;i<vBoxes.length;i++)
        {
            vBoxes[i]=new VBox();
            vBoxes[i].setAlignment(Pos.CENTER);
            for(int j=0;j<tiles.length;j++)
            {
                tiles[i][j]=new Tile(this);
                vBoxes[i].getChildren().add(tiles[i][j]);
            }
        }

        hBox.getChildren().addAll(vBoxes);
        getChildren().add(hBox);
    }

    private void setScale()
    {
        if (size==16)
        {
            setScaleX(getScaleX() * 0.7);
            setScaleY(getScaleY() * 0.7);
        }
        if (size==32)
        {
            setScaleX(getScaleX() * 0.4);
            setScaleY(getScaleY() * 0.4);
        }
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
