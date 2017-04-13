package media.graphics;

import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.image.ImageView;

public class Tile extends Button
{
    private ImageView imageView;
    private Board board;
    private boolean isEmpty;
            
    public Tile(Board b)
    {
        board=b;
        isEmpty=true;
        setMinSize(50, 50);
        setMaxSize(50, 50);
        imageView = new ImageView(board.emptyTileImage);
        setGraphic(imageView);
        setStyle("-fx-focus-color: transparent;");
        onClick();
    }
    
    private void onClick()
    {
        setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) {
                if (isEmpty)
                {
                    if (board.round%2==0)
                        putNewBlackPawn();
                    else 
                        putNewWhitePawn();
                    board.round++;
                }
            }
        });
    }
   
    private void setBlackImage()
    {
        imageView.setImage(board.blackPawnImage);
    }
    
    private void setWhiteImage()
    {
        imageView.setImage(board.whitePawnImage);
    } 
    
    public void putNewBlackPawn()
    {
        setBlackImage();
        isEmpty=false;
    }
    
    public void putNewWhitePawn()
    {
        setWhiteImage();
        isEmpty=false;
    }
}
