package game.board;

// TODO: optymalizacja, zamiast takiej ilosci ifow itp. dac kolejke i dodawac
// TODO: do niej pionki w ramach sprawdzania (lokalne kolejki scalane z duza)

public class MoveManager {
    private Grid grid;

    public MoveManager(Grid grid) {
        this.grid = grid;
    }

    public boolean checkMove(Point p, PawnColor playerColor) {
        if (checkVerticalUp(p, playerColor)) {
            for (int y = (int) p.getY() - 1; y >= 0; --y) {
                Pawn pawn = grid.getPawn(new Point(p.getX(), (double) y));

                if (pawn.getColor() != playerColor) {
                    pawn.changeOwner();
                } else {
                    break;
                }
            }
        }

        if (checkDownLeftDiagonal(p, playerColor)) {
            for (int x = (int) p.getX() - 1, y = (int) p.getY() + 1; x >= 0 && y < grid.getY(); --x, ++y) {
                Pawn pawn = grid.getPawn(new Point((double) x, y));

                if (pawn.getColor() != playerColor) {
                    pawn.changeOwner();
                } else {
                    break;
                }
            }
        }

        if (checkDownRightDiagonal(p, playerColor)) {
            for (int x = (int) p.getX() + 1, y = (int) p.getY() + 1; x < grid.getX() && y < grid.getY(); ++x, ++y) {
                Pawn pawn = grid.getPawn(new Point((double) x, y));

                if (pawn.getColor() != playerColor) {
                    pawn.changeOwner();
                } else {
                    break;
                }
            }
        }

        if (checkHorizontalLeft(p, playerColor)) {
            for (int x = (int) p.getX() -1; x >= 0; --x) {
                Pawn pawn = grid.getPawn(new Point((double) x, p.getY()));

                if (pawn.getColor() != playerColor){
                    pawn.changeOwner();
                }
                else{
                    break;
                }
            }
        }

        if (checkHorizontalRight(p, playerColor)) {
            for (int x = (int) p.getX() + 1; x < grid.getX(); ++x) {
                Pawn pawn = grid.getPawn(new Point((double) x, p.getY()));

                if (pawn.getColor() != playerColor) {
                    pawn.changeOwner();
                } else {
                    break;
                }

            }
        }

            if (checkTopLeftDiagonal(p, playerColor)) {
                for (int x = (int) p.getX() - 1, y = (int) p.getY() - 1; x >= 0 && y >= 0; --x, --y) {
                    Pawn pawn = grid.getPawn(new Point((double) x, y));

                    if (pawn.getColor() != playerColor) {
                        pawn.changeOwner();
                    } else {
                        break;
                    }

                }
            }

            if (checkTopRightDiagonal(p, playerColor)) {
                for (int x = (int) p.getX()+1, y = (int) p.getY()-1; x < grid.getX() && y >= 0; ++x, --y) {
                    Pawn pawn = grid.getPawn(new Point((double) x, y));

                    if (pawn.getColor() != playerColor){
                        pawn.changeOwner();
                    }
                    else{
                        break;
                    }
                }
            }

            if (checkVerticalDown(p, playerColor)) {
                for (int y = (int) p.getY() - 1; y >= 0; --y) {
                    Pawn pawn = grid.getPawn(new Point(p.getX(), (double) y));

                    if (pawn.getColor() != playerColor) {
                        pawn.changeOwner();
                    } else {
                        break;
                    }

                }
            }
            return true;
        }


    private boolean checkVerticalUp(Point p, PawnColor playerColor){
        boolean foundEnemyPawn = false;

        for (int y = (int)p.getY() -1; y >= 0; --y) {
            PawnColor currentColor = grid.getPawn(new Point(p.getX(), (double) y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            }
            else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            // nie potrzeba dalej sprawdzac warunkow
            // upraszczamy funkcje
            foundEnemyPawn = true;
        }

        return false;
    }

    private boolean checkVerticalDown(Point p, PawnColor playerColor){
        boolean foundEnemyPawn = false;

        for (int y = (int)p.getY() +1; y < grid.getY(); ++y) {
            PawnColor currentColor = grid.getPawn(new Point(p.getX(), (double) y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            }
            else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }

        return false;
    }

    private boolean checkHorizontalRight(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX() + 1; x < grid.getX(); ++x) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, p.getY())).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }

    private boolean checkHorizontalLeft(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX() -1; x >= 0; --x) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, p.getY())).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }

    private boolean checkTopRightDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX()+1, y = (int) p.getY()-1; x < grid.getX() && y >= 0; ++x, --y) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }

    private boolean checkDownRightDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX()+1, y = (int) p.getY()+1; x < grid.getX() && y < grid.getY(); ++x, ++y) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }

    private boolean checkDownLeftDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX()-1, y = (int) p.getY() + 1; x >= 0 && y < grid.getY(); --x, ++y) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }

    private boolean checkTopLeftDiagonal(Point p, PawnColor playerColor) {
        boolean foundEnemyPawn = false;

        for (int x = (int) p.getX()-1, y = (int) p.getY()-1; x >= 0 && y >= 0; --x, --y) {
            PawnColor currentColor = grid.getPawn(new Point((double) x, y)).getColor();

            if (foundEnemyPawn && currentColor == playerColor) {
                return true;
            } else if (currentColor == PawnColor.EMPTY || currentColor == playerColor) {
                break;
            }

            foundEnemyPawn = true;
        }
        return false;
    }
}
