package media.graphics;

import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.image.ImageView;

public class Tile extends Button
{
    ImageView imageView;
    Board board;
            
    public Tile(Board b)
    {
        board=b;
        setMinSize(50, 50);
        setMaxSize(50, 50);
        imageView = new ImageView(board.emptyTileImage);
        setGraphic(imageView);
        onClick();
    }
    
    private void onClick()
    {
        setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) {
                if (board.round%2==0)
                    changeToBlack();
                else 
                    changeToWhite();
                board.round++;
            }
        });
    }
   
    public void changeToBlack()
    {
        imageView.setImage(board.blackPawnImage);
    }
    
    public void changeToWhite()
    {
        imageView.setImage(board.whitePawnImage);
    }
  
}
