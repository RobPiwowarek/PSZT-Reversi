package media.graphics;

import game.board.Point;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mvc.GameView;

public class Board extends BorderPane {
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
        setupButtons();
    }

    public void putNewPawn(int x, int y) {
        if (round % 2 == 0)
            tiles[x][y].putNewBlackPawn();
        else
            tiles[x][y].putNewWhitePawn();

        round++;
    }

    public Turn getTurn() {
        if (round % 2 == 0)
            return Turn.BLACK_TURN;
        else
            return Turn.WHITE_TURN;
    }

    private void setupButtons() {
        MyButton passButton = new MyButton("Pass");
        setupPassButton(passButton);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(passButton);
        stackPane.setAlignment(Pos.CENTER_RIGHT);
        setPadding(new javafx.geometry.Insets(20));
        setRight(stackPane);
    }

    private void setupPassButton(Button passButton) {

    }

    private HBox setupEmptyTiles() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBoxes[] = new VBox[size];

        for (int i = 0; i < vBoxes.length; i++) {
            vBoxes[i] = new VBox();
            vBoxes[i].setAlignment(Pos.CENTER);
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this, new Point(i, j));
                vBoxes[i].getChildren().add(tiles[i][j]);
            }
        }

        hBox.getChildren().addAll(vBoxes);
        setCenter(hBox);

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
        setLeft(stackPane);
    }

    GameView getGameView() {
        return gameView;
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    private class MyButton extends Button {
        public MyButton(String text) {
            super(text);
            setMinSize(100, 40);
            setMaxSize(100, 40);
            setStyle("-fx-font: 12 arial;");
        }
    }
}


