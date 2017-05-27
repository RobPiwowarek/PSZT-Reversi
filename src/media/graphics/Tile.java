package media.graphics;

import game.board.Point;
import javafx.scene.control.Button;

public class Tile extends Button {
    private Point locationOnBoard;
    private final PawnSprite pawnSprite;
    private final Board board;
    private boolean isEmpty;

    public Tile(Board b, Point location) {
        board = b;
        isEmpty = true;
        pawnSprite = new PawnSprite();
        locationOnBoard = location;

        setMinSize(50, 50);
        setMaxSize(50, 50);
        setGraphic(pawnSprite);
        setStyle("-fx-focus-color: transparent;");

        onClick();
    }

    private void onClick() {
        // TODO: check if it is human's turn

        setOnAction(e -> {

            if (isEmpty && board.getGameView().humanMove(locationOnBoard)) {
                if (board.round % 2 == 0)
                    putNewBlackPawn();
                else
                    putNewWhitePawn();
                board.round++;
            }
        });
    }

    private void setBlackImage() {
        pawnSprite.setBlackPawn();
    }

    private void setWhiteImage() {
        pawnSprite.setWhitePawn();
    }

    public void putNewBlackPawn() {
        setBlackImage();
        isEmpty = false;
    }

    public void putNewWhitePawn() {
        setWhiteImage();
        isEmpty = false;
    }
}
