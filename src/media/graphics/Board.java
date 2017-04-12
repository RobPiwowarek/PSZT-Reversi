package media.graphics;

// TODO: size ma sie zmieniac od 8x8 do 32x32

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

public class Board extends StackPane
{
    private Tile[][] tiles;
    
    Image blackPawnImage;
    Image whitePawnImage;
    Image emptyTileImage;
    
    short size;
    int round;
    
    public Board()
    {
        loadImages();
        size=8;
        round=0;
        tiles = new Tile[size][size];
        setupEmptyTiles();
        startingPosition();
    }
    
    private void loadImages()
    {
        blackPawnImage = new Image(getClass().getResourceAsStream("black.png"));
        whitePawnImage = new Image(getClass().getResourceAsStream("white.png"));
        emptyTileImage = new Image(getClass().getResourceAsStream("empty.png"));
    }
    
    private void setupEmptyTiles()
    {
        VBox vBox = new VBox();
        HBox hBoxes[] = new HBox[size];
        for(int i=0;i<hBoxes.length;i++)
        {
            hBoxes[i]=new HBox();
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
        tiles[3][3].changeToWhite();
        tiles[4][4].changeToWhite();
        tiles[3][4].changeToBlack();
        tiles[4][3].changeToBlack();
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
