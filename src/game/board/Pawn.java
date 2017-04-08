package game.board;

public class Pawn {
    private PawnColor color;

    public Pawn() {
        this.color = PawnColor.EMPTY;
    }

    public void changeOwner() {
        if (color == PawnColor.EMPTY) {
            throw new IllegalStateException("game/board/Tile:Pawn: cannot change owner of an unplaced pawn");
        } else if (color == PawnColor.DARK) {
            color = PawnColor.LIGHT;
        } else {
            color = PawnColor.DARK;
        }
    }

    public PawnColor getColor() {
        return color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }
}
