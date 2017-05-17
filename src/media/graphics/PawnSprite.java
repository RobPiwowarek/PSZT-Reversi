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
        whitePawnImage = new Image("file:src/resources/sprites/white.png");
        blackPawnImage = new Image("file:src/resources/sprites/black.png");
        emptyTileImage = new Image("file:src/resources/sprites/empty.png");
    }
}