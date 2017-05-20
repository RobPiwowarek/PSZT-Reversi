package media.graphics;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mvc.GameView;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class Board extends StackPane {
    private final GameView gameView;

    private final Tile[][] tiles;

    private final int size;
    int round;
    private Text clockText;

    public Board(int s, GameView view) {
        gameView = view;
        round = 0;
        size = s;
        tiles = new Tile[size][size];

        setupClock();
        HBox hBox = setupEmptyTiles();
        startingPosition();
        setScale(hBox);
    }

    public void putNewPawn(int x, int y)
    {
        if (round%2==0)
            tiles[x][y].putNewBlackPawn();
        else
            tiles[x][y].putNewWhitePawn();
        round++;
    }

    private HBox setupEmptyTiles() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBoxes[] = new VBox[size];

        for (int i = 0; i < vBoxes.length; i++) {
            vBoxes[i] = new VBox();
            vBoxes[i].setAlignment(Pos.CENTER);
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this);
                vBoxes[i].getChildren().add(tiles[i][j]);
            }
        }

        hBox.getChildren().addAll(vBoxes);
        getChildren().add(hBox);

        return hBox;
    }

    private void setScale(HBox hBox) {
        if (size == 16) {
            hBox.setScaleX(getScaleX() * 0.7);
            hBox.setScaleY(getScaleY() * 0.7);
        }
        if (size == 32) {
            hBox.setScaleX(getScaleX() * 0.4);
            hBox.setScaleY(getScaleY() * 0.4);
        }
    }

    private void startingPosition() {
        int a = size / 2;
        int b = a - 1;

        tiles[a][a].putNewWhitePawn();
        tiles[b][b].putNewWhitePawn();
        tiles[a][b].putNewBlackPawn();
        tiles[b][a].putNewBlackPawn();
    }

    private void setupClock() {
        clockText = new Text("Kot");
        Font font = new Font("Arial Bold", 29);
        clockText.setFont(font);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_LEFT);
        stackPane.getChildren().add(clockText);
        getChildren().add(stackPane);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

}


