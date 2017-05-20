package game.board;

// TODO: size ma sie zmieniac od 8x8 do 32x32

public class GameBoard {
    private final short DEFAULT_BOARD_SIZE = 8;
    public final short MAX_BOARD_SIZE = 32;
    public final short MIN_BOARD_SIZE = 8;

    private MoveManager moveManager;

    private Grid grid;

    public GameBoard() {
        grid = new Grid(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
    }

    public GameBoard(short size){

        if (size <= MAX_BOARD_SIZE && size >= MIN_BOARD_SIZE){
            grid = new Grid(size, size);
        }
        else{
            //TODO:  Display Error Message if incorrect size
            grid = new Grid(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        }

        moveManager = new MoveManager(grid);

    }

    private boolean canPlace(Point p, PawnColor color) {
        return grid.isEmpty(p) && moveManager.checkMove(p, color);

    }

    public boolean placePawn(Point p){
        if (canPlace(p, getPawn(p).getColor())){
            moveManager.flipPawns();
        }
        else return false;

        return true;
    }

    private Pawn getPawn(Point p){
        return grid.getPawn(p);
    }

}
