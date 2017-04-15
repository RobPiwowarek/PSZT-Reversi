package media.graphics;

import javafx.scene.image.*;

public class PawnSprite extends ImageView
{
    static ImageManager imageManager = new ImageManager();
    
    PawnSprite()
    {
        setImage(imageManager.emptyTileImage);
    }
    
    public void setBlackPawn()
    {
        setImage(imageManager.blackPawnImage);
    }
    
    public void setWhitePawn()
    {
        setImage(imageManager.whitePawnImage);
    }
}

class ImageManager
{
    public Image blackPawnImage;
    public Image whitePawnImage;
    public Image emptyTileImage;
    
    ImageManager()
    {
        whitePawnImage = new Image(getClass().getResourceAsStream("white.png"));
        blackPawnImage = new Image(getClass().getResourceAsStream("black.png"));
        emptyTileImage = new Image(getClass().getResourceAsStream("empty.png"));
    }
}