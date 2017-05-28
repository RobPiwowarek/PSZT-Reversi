package game.board;

public class Tile {
    private Pawn pawn;
    private Point point;

    public Tile(Point point) {
        this.point = point;
        this.pawn = new Pawn();
    }

    public void placePawn(PawnColor color) {
        if (color != PawnColor.EMPTY) {
            pawn.setColor(color);
        } else {
            throw new IllegalStateException("game/board/Tile: cannot place pawn with color EMPTY. To reset the tile use clear().");
        }
    }

    public void changeOwner() {
        pawn.changeOwner();
    }

    public PawnColor getOwner() {
        return pawn.getColor();
    }

    public Point getPoint() {
        return point;
    }

    public void clear() {
        pawn.setColor(PawnColor.EMPTY);
    }

    public Pawn getPawn() {
        return pawn;
    }
}
