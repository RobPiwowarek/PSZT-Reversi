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
        setOnAction(e -> {
            if (board.getGameView().getGameController().isCurrentPlayerHuman()) {
                if (isEmpty) {
                    board.getGameView().move(locationOnBoard);
                }
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
