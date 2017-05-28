package game.board;

import game.Player;
import game.actions.Action;
import game.actions.Pass;
import game.actions.Place;
import mvc.Controller;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameModel implements game.ai.Game, Cloneable {
    private GameBoard board;

    private int nplayer = 0;
    private int nopponent = 1;

    private Player[] players;
    private int whitePawnCount;
    private int blackPawnCount;
    private int passCount;

    public Player getCurrentPlayer() {
        return players[nplayer];
    }

    public PawnColor getCurrentPlayerColor() {
        return players[nplayer].getColor();
    }

    public void setPlayers(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
    }

    public void switchPlayers() {
        int tmp = nopponent;
        nopponent = nplayer;
        nplayer = tmp;
    }

    public GameModel() {
        this.players = new Player[2];
        this.passCount = 0;
        blackPawnCount = whitePawnCount = 2;
    }

    public void setSize(short size) {

        this.board = new GameBoard(size);
        this.board.setStartingPawns();

    }

    public GameModel clone() {
        GameModel cloned = new GameModel();
        cloned.setSize(getSize());
        cloned.setPlayers(players[0], players[1]);
        cloned.nplayer = nplayer;
        cloned.nopponent = nopponent;
        cloned.blackPawnCount = blackPawnCount;
        cloned.whitePawnCount = whitePawnCount;
        cloned.passCount = passCount;
        cloned.board = board.clone();
        return cloned;
    }

    public void makeMove(Action move) {
        int flipCount;
        Point p = move.getPoint();
        if (p.getX() == -1 && p.getY() == -1) { // pass
            ++passCount;
            switchPlayers();
            return;
        }
        flipCount = board.placePawn(p, getCurrentPlayerColor());
        if (flipCount == 0) {
            switchPlayers();
            return;
        }
        if (getCurrentPlayerColor() == PawnColor.DARK) {
            blackPawnCount += flipCount + 1;
            whitePawnCount -= flipCount;
        } else {
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
        for (int x = 0; x < board.getSize(); ++x) {
            for (int y = 0; y < board.getSize(); ++y) {
                if (board.canPlace(new Point(x, y), color)) {
                    actions.add(new Place(x, y));
                }
                board.clearPawnsQueue();
            }
        }
        if (actions.size() == 0) {
            actions.add(new Pass());
        }
        return actions;
    }

    public boolean canPlace(Point p) {
        return this.board.canPlace(p, getCurrentPlayerColor());
    }

    public void placePawn(Point p) {
        board.placePawnWithoutChecking(p, getCurrentPlayerColor());
    }

    public boolean isOver() {
        // board full or both players were forced to pass or a player lost all their pawns
        return (blackPawnCount + whitePawnCount == board.getSize() * board.getSize()) || (passCount >= 2) || (blackPawnCount == 0) || (whitePawnCount == 0);
    }

    @Override
    public int getBlackPawnCount() {
        return blackPawnCount;
    }

    @Override
    public int getWhitePawnCount() {
        return whitePawnCount;
    }

    @Override
    public int getCurrentColorAsInt() {
        PawnColor c = getCurrentPlayerColor();
        if (c == PawnColor.DARK) {
            return 1;
        }
        if (c == PawnColor.LIGHT) {
            return -1;
        }
        return 0;
    }

    @Override
    public int getPawnAsInt(int x, int y) {
        PawnColor c = board.getPawn(new Point(x, y)).getColor();
        if (c == PawnColor.DARK) {
            return 1;
        }
        if (c == PawnColor.LIGHT) {
            return -1;
        }
        return 0;
    }

    @Override
    public short getSize() {
        return board.getSize();
    }

    public void setController(Controller c) {
        board.setController(c);
    }
}
