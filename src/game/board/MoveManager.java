package game.board;

// verify moves
import java.util.ArrayDeque;

public class MoveManager {
    private Grid grid;

    private ArrayDeque<Pawn> pawnsToFlip = new ArrayDeque<Pawn>();

    public MoveManager(Grid grid) {
        this.grid = grid;
    }

    public boolean checkMove(Point p, PawnColor playerColor){
        PawnColor enemyColor;
        PawnColor currentColor;
        boolean adjacentPawnIsEnemy;

        if(playerColor == PawnColor.DARK){
            enemyColor = PawnColor.LIGHT;
        }
        else {
            enemyColor = PawnColor.DARK;
        }

        // VERTICAL UP
        adjacentPawnIsEnemy = false;
        for (int y = (int)p.getY() -1; y >= 0; --y)
        {
            currentColor = grid.getPawn(new Point(p.getX(), (double)y)).getColor();
            if(!adjacentPawnIsEnemy){
                if(currentColor == enemyColor){
                    adjacentPawnIsEnemy = true;
                }
                else break;
            }
            else
                if(currentColor == PawnColor.EMPTY){
                    break;
                }
                if(currentColor == playerColor){
                    for(int y2 = (int)p.getY() - 1; y2 > y; --y)
                    {
                        pawnsToFlip.add(grid.getPawn(new Point(p.getX(), (double)y2)));
                    }
                    break;
                }
        }

        return true;
    }

    public void changeOwnership(){
        pawnsToFlip.forEach(Pawn::changeOwner);
    }

    public boolean checkMoveAndChangeOwnership(Point p, PawnColor color){
        if (checkMove(p, color)){
            changeOwnership();
            return true;
        }
        else{
            return false;
        }
    }

}
