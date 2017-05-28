package game.board;

public class Grid {
    private short x;
    private short y;

    Tile[][] tiles;

    public Grid(short x, short y){
        this.x = x;
        this.y = y;

        tiles = new Tile[y][x];
        for(int i = 0; i < this.y; ++i)
        {
            for(int j = 0; j < this.x; ++j)
            {
                tiles[i][j] = new Tile(new Point(i,j));
            }
        }
    }

    boolean addPawn(Point p, PawnColor color){
        tiles[(int)p.getY()][(int)p.getX()].placePawn(color);

        return true;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    boolean isEmpty(Point p){
        return tiles[(int) p.getY()][(int) p.getX()].getOwner() == PawnColor.EMPTY;
    }

    Tile getTile(Point p){
        return tiles[(int)p.getY()][(int)p.getX()];
    }

    Pawn getPawn(Point p){
        return tiles[(int)p.getY()][(int)p.getX()].getPawn();
    }
}
