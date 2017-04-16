package media.graphics;

import javafx.scene.control.Button;
import javafx.event.*;

public class Tile extends Button
{
    private final PawnSprite pawnSprite;
    private final Board board;
    private boolean isEmpty;
            
    public Tile(Board b)
    {
        board=b;
        isEmpty=true;
        pawnSprite=new PawnSprite();
        
        setMinSize(50, 50);
        setMaxSize(50, 50);
        setGraphic(pawnSprite);
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
        pawnSprite.setBlackPawn();
    }
    
    private void setWhiteImage()
    {
        pawnSprite.setWhitePawn();
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
