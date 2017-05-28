package game.board;

import game.PlayerType;
import game.actions.Action;
import game.actions.Pass;
import game.actions.Place;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class GameModel implements game.ai.Game, Cloneable {
    private GameBoard board;

    private int nplayer = 0;
    private int nopponent = 1;

    private PlayerType[] players;
    private int whitePawnCount;
    private int blackPawnCount;
    private int passCount;

    public PlayerType getCurrentPlayer() {
        return players[nplayer];
    }
    public PawnColor getCurrentPlayerColor() { return players[nplayer].getColor(); }
    public void setPlayers(PlayerType p1, PlayerType p2)
    {
        players[0] = p1;
        players[1] = p2;
    }

    public void switchPlayers() {
        int tmp = nopponent;
        nopponent = nplayer;
        nplayer = tmp;
    }

    public GameModel(short size) {
        this.players = new PlayerType[2];
        this.passCount = 0;
        this.board = new GameBoard(size);
        this.board.setStartingPawns();
        blackPawnCount = whitePawnCount = 2;
    }

    public GameModel clone() {
        GameModel cloned = new GameModel(board.getSize());
        cloned.nplayer = nplayer;
        cloned.nopponent = nopponent;
        cloned.blackPawnCount = blackPawnCount;
        cloned.whitePawnCount = whitePawnCount;
        cloned.passCount = passCount;
        cloned.players = Arrays.copyOf(players, 2);
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
        flipCount = board.placePawn(p, getCurrentPlayerColor());
        if(flipCount == 0) {
            switchPlayers();
            return;
        }
        if(getCurrentPlayerColor() == PawnColor.DARK) {
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
        return getPossibleMoves(getCurrentPlayerColor());
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
        if(getCurrentPlayerColor() == PawnColor.DARK) {
            return blackPawnCount - whitePawnCount;
        }
        else {
            return whitePawnCount - blackPawnCount;
        }
    }

    public boolean canPlace(Point p){
        return this.board.canPlace(p, getCurrentPlayerColor());
    }

    public void placePawn(Point p){
        board.placePawnWithoutChecking(p, getCurrentPlayerColor());
    }

    public boolean isOver() {
        // board full or both players were forced to pass or a player lost all their pawns
        return (blackPawnCount + whitePawnCount == board.getSize()*board.getSize()) || (passCount >= 2) || (blackPawnCount == 0) || (whitePawnCount == 0);
    }
}
