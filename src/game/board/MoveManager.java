package game.board;

import java.util.ArrayDeque;

public class MoveManager {
    private Grid grid;

    private ArrayDeque<Pawn> pawnsToFlip = new ArrayDeque<Pawn>();

    public MoveManager(Grid grid) {
        this.grid = grid;
    }

    public boolean checkMove(Point p, PawnColor playerColor) {
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i != 0 || j != 0) {
                    if (checkInDirection(p, playerColor, new Point(i, j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // direction = (-1,-1), (1,1), (0,1) (1,0), (0,-1), (-1,0) (-1,1) (1,-1)
    private boolean checkInDirection(Point p, PawnColor playerColor, Point direction) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();
        int dx = (int) direction.getX();
        int dy = (int) direction.getY();
        Pawn pawn;
        PawnColor currentColor;
        Point currentPoint = new Point(p.getX() + dx, p.getY() + dy);

        while (currentPoint.isInGrid(grid)) {
            pawn = grid.getPawn(currentPoint);
            currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }

            currentPoint.translate(dx, dy);
        }

        return false;
    }

    // flip and clear could be easily merged if necessary.

    int flipPawns() {
        int size = pawnsToFlip.size();
        for (Pawn p : pawnsToFlip) {
            p.changeOwner();
        }
        clearPawnsQueue();
        return size;
    }

    void clearPawnsQueue() {
        pawnsToFlip.clear();
    }

}
