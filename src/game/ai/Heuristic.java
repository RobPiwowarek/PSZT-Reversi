package game.ai;

public class Heuristic {
    private int[][] heuristicTable;
    private short boardSize;

    public void setSize(short size)
    {
        this.boardSize = size;
        this.heuristicTable = new int[size][size];

        // fill with 1s initially
        for(int i = 0; i < size; ++i)
        {
            for(int j = 0; j < size; ++j)
            {
                this.heuristicTable[i][j] = 1;
            }
        }
        // corners and their adjacent area
        for(int i = 0; i <= 1; ++ i)
        {
            for (int j = 0; j <= 1; ++j)
            {
                this.heuristicTable[i*(size-1)][j*(size-1)] = 65;
                this.heuristicTable[i*(size-1)][j*(size-3)+1] = -3;
                this.heuristicTable[i*(size-3)+1][j*(size-1)] = -3;
                this.heuristicTable[i*(size-3)+1][j*(size-3)+1] = -29;
                this.heuristicTable[i*(size-1)][j*(size-5)+2] = 6;
                this.heuristicTable[i*(size-5)+2][j*(size-1)] = 6;
                this.heuristicTable[i*(size-3)+1][j*(size-5)+2] = 3;
                this.heuristicTable[i*(size-5)+2][j*(size-3)+1] = 3;
                this.heuristicTable[i*(size-5)+2][j*(size-5)+2] = 5;
            }
        }
        // edges
        for(int i = 3; i <= size - 4; ++i) {
            for(int j = 0; j <= 1; ++j) {
                this.heuristicTable[i][j*(size-1)] = 4;
                this.heuristicTable[j*(size-1)][i] = 4;
                this.heuristicTable[i][j*(size-5)+2] = 3;
                this.heuristicTable[j*(size-5)+2][i] = 3;
            }
        }
    }

    public int getScoring(Game game)
    {
        int boardNTiles = boardSize * boardSize;
        int blackPawnCount = game.getBlackPawnCount();
        int whitePawnCount = game.getWhitePawnCount();
        int tileDifference = (blackPawnCount - whitePawnCount) * game.getCurrentColorAsInt();
        if(blackPawnCount + whitePawnCount < boardNTiles / 2)
        {
            int h;
            int count = 0;
            for (int y = 0; y < boardSize; ++y)
            {
                for (int x = 0; x < boardSize; ++x)
                {
                    h = heuristicTable[y][x] * (game.getCurrentColorAsInt()) * (game.getPawnAsInt(x,y));
                    count += h;
                }
            }
            return count;
        }
        else {
            return tileDifference;
        }
    }
}