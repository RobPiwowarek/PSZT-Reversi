package game.board;

import game.actions.Action;
import game.actions.Pass;
import game.actions.Place;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameModel implements game.ai.Game, Cloneable {
    private GameBoard board;

    public PawnColor getCurrentPlayer() {
        return currentPlayer;
    }

    private PawnColor currentPlayer;
    private int whitePawnCount;
    private int blackPawnCount;
    private int passCount;

    Player player;
    Player opponent;

    public void switchPlayers() {
        if(currentPlayer == PawnColor.DARK) {
            currentPlayer = PawnColor.LIGHT;
        }
        else {
            currentPlayer = PawnColor.DARK;
        }
    }

    private void init(short size, PawnColor startingPlayer) {
        this.passCount = 0;
        this.board = new GameBoard(size);
        this.board.setStartingPawns();
        currentPlayer = startingPlayer;
        blackPawnCount = whitePawnCount = 2;
    }
    public GameModel(short size) {
        init(size, PawnColor.LIGHT);
    }

    public GameModel(short size, PawnColor startingPlayer) {
        init(size, startingPlayer);
    }

    public GameModel clone() {
        GameModel cloned = new GameModel(board.getSize());
        cloned.currentPlayer = currentPlayer;
        cloned.blackPawnCount = blackPawnCount;
        cloned.whitePawnCount = whitePawnCount;
        cloned.passCount = passCount;
        cloned.board = board.clone();
        return cloned;
    }

    public void makeMove(Action move) {
        int flipCount;
        Point p = move.getPoint();
        if(p == null) { // pass
            ++passCount;
            switchPlayers();
            return;
        }
        flipCount = board.placePawn(p, currentPlayer);
        if(flipCount == 0) {
            switchPlayers();
            return;
        }
        if(currentPlayer == PawnColor.DARK) {
            blackPawnCount += flipCount + 1;
            whitePawnCount -= flipCount;
        }
        else {
            blackPawnCount -= flipCount;
            whitePawnCount += flipCount + 1;
        }
        passCount = 0;
        switchPlayers();
    }

    public Deque<Action> getPossibleMoves() {
        return getPossibleMoves(currentPlayer);
    }

    public Deque<Action> getPossibleMoves(PawnColor color) {
        //TODO - optimize
        ArrayDeque<Action> actions = new ArrayDeque<>();
        for(int x = 0; x < board.getSize(); ++x) {
            for(int y = 0; y < board.getSize(); ++y) {
                if(board.canPlace(new Point(x,y), color)) {
                    actions.add(new Place(x, y));
                }
                board.clearPawnsQueue();
            }
        }
        if(actions.size() == 0) {
            actions.add(new Pass());
        }
        return actions;
    }

    public int getScoring() {
        // TODO - better heuristic scoring
        if(currentPlayer == PawnColor.LIGHT) {
            return blackPawnCount - whitePawnCount;
        }
        else {
            return whitePawnCount - blackPawnCount;
        }
    }

    public boolean canPlace(Point p){
        return this.board.canPlace(p, currentPlayer);
    }

    public void placePawn(Point p){
        board.placePawnWithoutChecking(p, currentPlayer);
    }

    public boolean isOver() {
        // board full or both players were forced to pass or a player lost all their pawns
        return (blackPawnCount + whitePawnCount == board.getSize()*board.getSize()) || (passCount >= 2) || (blackPawnCount == 0) || (whitePawnCount == 0);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
