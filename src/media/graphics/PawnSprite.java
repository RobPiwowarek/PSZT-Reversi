package media.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PawnSprite extends ImageView {
    static ImageManager imageManager = new ImageManager();
    private int color;

    public int getColor() {
        return color;
    }
    PawnSprite() {
        this.color = 0;
        setImage(imageManager.emptyTileImage);
    }

    public void setBlackPawn() {
        this.color = 1;
        setImage(imageManager.blackPawnImage);
    }

    public void setWhitePawn() {
        this.color = -1;
        setImage(imageManager.whitePawnImage);
    }
}

class ImageManager {
    public Image blackPawnImage;
    public Image whitePawnImage;
    public Image emptyTileImage;

    ImageManager() {
        whitePawnImage = new Image("file:src/resources/sprites/white.png");
        blackPawnImage = new Image("file:src/resources/sprites/black.png");
        emptyTileImage = new Image("file:src/resources/sprites/empty.png");
    }
}