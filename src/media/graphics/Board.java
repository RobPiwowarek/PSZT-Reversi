package media.graphics;

import game.board.Point;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.GameView;
import main.Main;

public class Board extends BorderPane {
    private final GameView gameView;
    private final Tile[][] tiles;
    private final int size;
    private int round;
    private Text clockText;
    private Text scoreText;
    private Text turnText;
    private HBox boardHBox;

    public Board(int s, GameView view) {
        gameView = view;
        round = 0;
        size = s;
        tiles = new Tile[size][size];

        setupClockText();
        HBox hBox = setupEmptyTiles();
        startingPosition();
        setScale(hBox);
        setupButtons();
        setupTurnText();
        setupScoreText();
    }

    public void putNewPawn(int x, int y) {
        if (getTurn() == Turn.BLACK_TURN) {
            tiles[x][y].putNewBlackPawn();
            turnText.setText("White turn");
        }
        else {
            tiles[x][y].putNewWhitePawn();
            turnText.setText("Black turn");
        }

        scoreText.setText(getScore());
        round++;
    }

    private String getScore()
    {
        return gameView.getGameController().getScore();
    }

    public void registerPass() {
        if (getTurn() == Turn.BLACK_TURN) {
            turnText.setText("White turn");
        }
        else {
            turnText.setText("Black turn");
        }
        round++;
    }

    public void flipPawn(int x, int y) {
        tiles[x][y].flip();
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

        MyButton endButton = new MyButton("End game");
        setupEndButton(endButton);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(passButton, endButton);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        setPadding(new javafx.geometry.Insets(20));
        setRight(vBox);
    }

    private void setupPassButton(Button passButton) {
        passButton.setOnAction(event -> {
            if (getGameView().getGameController().isCurrentPlayerHuman()) {
                getGameView().pass();
            }
        });

    }

    private void setupEndButton(Button endButton) {
        endButton.setOnAction(event -> {
            Main.restart(new Stage());
        });
    }

    private HBox setupEmptyTiles() {
        boardHBox = new HBox();
        VBox vBoxes[] = new VBox[size];

        for (int i = 0; i < vBoxes.length; i++) {
            vBoxes[i] = new VBox();
            vBoxes[i].setAlignment(Pos.CENTER);
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this, new Point(i, j));
                vBoxes[i].getChildren().add(tiles[i][j]);
            }
        }

        boardHBox.setMinSize(1,1);
        setCenter(boardHBox);
        boardHBox.setAlignment(Pos.CENTER);
        boardHBox.getChildren().addAll(vBoxes);

        return boardHBox;
    }

    private void setScale(HBox hBox) {
        if (size == 16) {
            hBox.setScaleX(hBox.getScaleX() * 0.7);
            hBox.setScaleY(hBox.getScaleY() * 0.7);
        }
        if (size == 32) {
            hBox.setScaleX(hBox.getScaleX() * 0.4);
            hBox.setScaleY(hBox.getScaleY() * 0.4);
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

    private void setupClockText() {
        clockText = new MyText("Kot");

        setLeft(clockText);
    }

    private void setupTurnText() {
        turnText = new MyText("Black turn");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(turnText);
        stackPane.setAlignment(Pos.CENTER);
        setBottom(stackPane);
    }

    private void setupScoreText() {
        scoreText = new MyText("2:2");
        Text text1 = new MyText("Black");
        Text text2 = new MyText("White");

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(text1,scoreText,text2);
        hBox.setAlignment(Pos.TOP_CENTER);
        setTop(hBox);
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

    public void setTurnText(String text)
    {
        turnText.setText(text);
    }

    public void disable()
    {
        boardHBox.setMouseTransparent(true);
    }

    public void enable()
    {
        boardHBox.setMouseTransparent(false);
    }

    private class MyButton extends Button {
        public MyButton(String text) {
            super(text);
            setMinSize(100, 40);
            setMaxSize(100, 40);
            setStyle("-fx-font: 12 arial;");
        }
    }

    private class MyText extends Text {
        public MyText(String string)  {
            super(string);
            Font font = new Font("Arial Bold", 29);
            setFont(font);
        }
    }

}


