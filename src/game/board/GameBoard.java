package game.board;

// TODO: size ma sie zmieniac od 8x8 do 32x32

import mvc.Controller;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class GameBoard {
    private final short DEFAULT_BOARD_SIZE = 8;
    public final short MAX_BOARD_SIZE = 32;
    public final short MIN_BOARD_SIZE = 8;

    public short getSize() {
        return size;
    }

    private short size;
    private MoveManager moveManager;
    private Grid grid;

    public GameBoard() {
        this.size = DEFAULT_BOARD_SIZE;
        grid = new Grid(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
    }

    public GameBoard clone() {
        GameBoard cloned = new GameBoard(size);
        Point p;
        PawnColor c;
        // TODO - optimize
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                p = new Point(x, y);
                c = getPawn(p).getColor();
                if (c != PawnColor.EMPTY) {
                    cloned.grid.addPawn(p, c);
                }
            }
        }
        return cloned;
    }

    public GameBoard(short size) {
        this.size = size;
        if (size <= MAX_BOARD_SIZE && size >= MIN_BOARD_SIZE) {
            grid = new Grid(size, size);
        } else {
            //TODO:  Display Error Message if incorrect size
            grid = new Grid(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        }

        moveManager = new MoveManager(grid);

    }

    boolean canPlace(Point p, PawnColor color) {
        return grid.isEmpty(p) && moveManager.checkMove(p, color);

    }

    void clearPawnsQueue() {
        moveManager.clearPawnsQueue();
    }

    public int placePawn(Point p, PawnColor color) {
        grid.addPawn(p, color);
        return moveManager.flipPawns();
    }

    public ArrayDeque<Tile> placePawnAI(Point p, PawnColor color) {
        grid.addPawn(p, color);
        return moveManager.flipPawnsAI();
    }

    public void undoFlipPawns(ArrayDeque<Tile> tiles) {
        moveManager.undoFlipPawns(tiles);
    }

    public void setStartingPawns() {
        int a = grid.getX() / 2;
        int b = a - 1;

        grid.addPawn(new Point(a, a), PawnColor.LIGHT);
        grid.addPawn(new Point(b, b), PawnColor.LIGHT);
        grid.addPawn(new Point(a, b), PawnColor.DARK);
        grid.addPawn(new Point(b, a), PawnColor.DARK);
    }

    void clear() {
        grid.clear();
    }

    Pawn getPawn(Point p) {
        return grid.getPawn(p);
    }
    Tile getTile(Point p) { return grid.getTile(p); }

    void setController(Controller c){
        moveManager.setController(c);
    }

}
