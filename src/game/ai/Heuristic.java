package game.ai;

public class Heuristic {
    private int[][] heuristicTable;
    private short boardSize;

    public void setSize(short size) {
        this.boardSize = size;
        this.heuristicTable = new int[size][size];

        // fill with 1s initially
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                this.heuristicTable[i][j] = 1;
            }
        }
        // corners and their adjacent area
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= 1; ++j) {
                this.heuristicTable[i * (size - 1)][j * (size - 1)] = 65;
                this.heuristicTable[i * (size - 1)][j * (size - 3) + 1] = -3;
                this.heuristicTable[i * (size - 3) + 1][j * (size - 1)] = -3;
                this.heuristicTable[i * (size - 3) + 1][j * (size - 3) + 1] = -29;
                this.heuristicTable[i * (size - 1)][j * (size - 5) + 2] = 6;
                this.heuristicTable[i * (size - 5) + 2][j * (size - 1)] = 6;
                this.heuristicTable[i * (size - 3) + 1][j * (size - 5) + 2] = 3;
                this.heuristicTable[i * (size - 5) + 2][j * (size - 3) + 1] = 3;
                this.heuristicTable[i * (size - 5) + 2][j * (size - 5) + 2] = 5;
            }
        }
        // edges
        for (int i = 3; i <= size - 4; ++i) {
            for (int j = 0; j <= 1; ++j) {
                this.heuristicTable[i][j * (size - 1)] = 4;
                this.heuristicTable[j * (size - 1)][i] = 4;
                this.heuristicTable[i][j * (size - 5) + 2] = 3;
                this.heuristicTable[j * (size - 5) + 2][i] = 3;
            }
        }
    }

    public int getScoring(Game game, int AIColor) {
        int blackPawnCount = game.getBlackPawnCount();
        int whitePawnCount = game.getWhitePawnCount();
        int tileDifference = (blackPawnCount - whitePawnCount)*AIColor;
        int inf = 10000; // good enough here probably
        if(game.isOver()) {
            if(tileDifference > 0) {
                return inf + tileDifference;
            }
            else if(tileDifference < 0){
                return -inf + tileDifference;
            }
            else {
                return 0;
            }
        }
        int relativeTileDifference;
        int AIPawnCount = AIColor == 1 ? blackPawnCount : whitePawnCount;
        int OppPawnCount = AIColor == 1 ? whitePawnCount : blackPawnCount;
        if(AIPawnCount > OppPawnCount) {
            relativeTileDifference = 100*AIPawnCount/(AIPawnCount + OppPawnCount);
        }
        else if(OppPawnCount > AIPawnCount) {
            relativeTileDifference = -100*OppPawnCount/(AIPawnCount + OppPawnCount);
        }
        else {
            relativeTileDifference = 0;
        }

        //mobility
        int aimoves = game.getNPossibleMoves(AIColor);
        int oppmoves = game.getNPossibleMoves(-AIColor);
        int mobility = 0;
        if(aimoves > oppmoves) {
            mobility = 100*aimoves/(aimoves + oppmoves);
        }
        else if(oppmoves > aimoves) {
            mobility = -100*oppmoves/(aimoves + oppmoves);
        }

        //pawn positions
        int h;
        int positions = 0;
        for (int y = 0; y < boardSize; ++y) {
            for (int x = 0; x < boardSize; ++x) {
                h = heuristicTable[y][x] * (AIColor) * (game.getPawnAsInt(x, y));
                positions += h;
            }
        }

        // corners
        int blackCorners = 0;
        int whiteCorners = 0;
        int pawnColor;
        int size = game.getSize();
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                pawnColor = game.getPawnAsInt(i*(size-1), j*(size-1));
                if(pawnColor == 1) {
                    ++blackCorners;
                }
                else if(pawnColor == -1) {
                    ++whiteCorners;
                }
            }
        }
        int corners = 0;
        if(blackCorners + whiteCorners != 0) {
            corners = (25* (blackCorners - whiteCorners) * AIColor) / (blackCorners + whiteCorners);
        }

        // close to corners
        int blackCloseToCorners = 0;
        int whiteCloseToCorners = 0;
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                if(game.getPawnAsInt(i*(size-1), j*(size-1)) == 0) { // empty corner
                    int color;
                    color = game.getPawnAsInt(i*(size-1), j*(size-3) + 1);
                    if(color == 1) {
                        ++blackCloseToCorners;
                    }
                    else if(color == -1) {
                        ++whiteCloseToCorners;
                    }
                    color = game.getPawnAsInt(i*(size-3) + 1, j*(size-1));
                    if(color == 1) {
                        ++blackCloseToCorners;
                    }
                    else if(color == -1) {
                        ++whiteCloseToCorners;
                    }
                    color = game.getPawnAsInt(i*(size-3) + 1, j*(size-3) + 1);
                    if(color == 1) {
                        ++blackCloseToCorners;
                    }
                    else if(color == -1) {
                        ++whiteCloseToCorners;
                    }
                }
            }
        }
        int closeCorners = -12*(blackCloseToCorners - whiteCloseToCorners)*AIColor;

        return 10*relativeTileDifference + 78*mobility + 10*positions + 801*corners + 382*closeCorners;
    }
}