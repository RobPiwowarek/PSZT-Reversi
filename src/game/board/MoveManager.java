package game.board;

import java.util.ArrayDeque;

public class MoveManager {
    private Grid grid;

    private ArrayDeque<Pawn> pawnsToFlip = new ArrayDeque<Pawn>();

    public MoveManager(Grid grid) {
        this.grid = grid;
    }

    public boolean checkMove(Point p, PawnColor playerColor) {
        return checkVerticalUp(p, playerColor) | checkDownLeftDiagonal(p, playerColor) | checkDownRightDiagonal(p, playerColor) | checkHorizontalLeft(p, playerColor) |
                checkHorizontalRight(p, playerColor) | checkTopLeftDiagonal(p, playerColor) | checkTopRightDiagonal(p, playerColor) | checkVerticalDown(p, playerColor);

    }

    private boolean checkVerticalUp(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int y = (int) p.getY() - 1; y >= 0; --y) {
            Pawn pawn = grid.getPawn(new Point(p.getX(), (double) y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }

            // nie potrzeba dalej sprawdzac warunkow
            // upraszczamy funkcje
        }

        return false;
    }

    private boolean checkVerticalDown(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int y = (int) p.getY() + 1; y < grid.getY(); ++y) {
            Pawn pawn = grid.getPawn(new Point(p.getX(), (double) y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }

        }

        return false;
    }

    private boolean checkHorizontalRight(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() + 1; x < grid.getX(); ++x) {
            Pawn pawn = grid.getPawn(new Point((double) x, p.getY()));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }

        }
        return false;
    }

    private boolean checkHorizontalLeft(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() - 1; x >= 0; --x) {
            Pawn pawn = grid.getPawn(new Point((double) x, p.getY()));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    private boolean checkTopRightDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() + 1, y = (int) p.getY() - 1; x < grid.getX() && y >= 0; ++x, --y) {
            Pawn pawn = grid.getPawn(new Point((double) x, y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }

        }
        return false;
    }

    private boolean checkDownRightDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() + 1, y = (int) p.getY() + 1; x < grid.getX() && y < grid.getY(); ++x, ++y) {
            Pawn pawn = grid.getPawn(new Point((double) x, y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    private boolean checkDownLeftDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() - 1, y = (int) p.getY() + 1; x >= 0 && y < grid.getY(); --x, ++y) {
            Pawn pawn = grid.getPawn(new Point((double) x, y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    private boolean checkTopLeftDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;
        ArrayDeque<Pawn> potentialPawnsToFlip = new ArrayDeque<Pawn>();

        for (int x = (int) p.getX() - 1, y = (int) p.getY() - 1; x >= 0 && y >= 0; --x, --y) {
            Pawn pawn = grid.getPawn(new Point((double) x, y));
            PawnColor currentColor = pawn.getColor();

            if (currentColor != PawnColor.EMPTY && currentColor != playerColor) {
                potentialPawnsToFlip.add(pawn);
                foundEnemyPawn = true;
            } else if (foundEnemyPawn && currentColor == playerColor) {
                pawnsToFlip.addAll(potentialPawnsToFlip);
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    // flip and clear could be easily merged if necessary.

    private void flipPawns() {
        for (Pawn p : pawnsToFlip) {
            p.changeOwner();
        }
    }

    private void clearPawnsQueue(){
        pawnsToFlip.clear();
    }
}
