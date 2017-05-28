package game.board;

import mvc.Controller;

import java.util.ArrayDeque;

public class MoveManager {
    private Grid grid;
    private Controller controller;
    private ArrayDeque<Tile> tilesToFlip = new ArrayDeque<Tile>();

    public MoveManager(Grid grid) {
        this.grid = grid;
    }

    public boolean checkMove(Point p, PawnColor playerColor) {
        boolean result = false;
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i != 0 || j != 0) {
                    if (checkInDirection(p, playerColor, new Point(i, j))) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    // direction = (-1,-1), (1,1), (0,1) (1,0), (0,-1), (-1,0) (-1,1) (1,-1)
    private boolean checkInDirection(Point p, PawnColor playerColor, Point direction) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Tile> potentialTilesToFlip = new ArrayDeque<Tile>();
        int dx = (int) direction.getX();
        int dy = (int) direction.getY();
        Tile tile;
        PawnColor currentColor;
        Point currentPoint = new Point(p.getX() + dx, p.getY() + dy);

        while (currentPoint.isInGrid(grid)) {
            tile = grid.getTile(currentPoint);
            currentColor = tile.getPawn().getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialTilesToFlip.add(tile);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                tilesToFlip.addAll(potentialTilesToFlip);
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
        int size = tilesToFlip.size();
        for (Tile t : tilesToFlip) {
            Point p = t.getPoint();
            if(this.controller != null) {
                this.controller.flipPawn((int) p.getX(), (int) p.getY());
            }
            t.getPawn().changeOwner();

        }
        clearPawnsQueue();
        return size;
    }

    void clearPawnsQueue() {
        tilesToFlip.clear();
    }

    void setController(Controller c) {
        this.controller = c;
    }

}
