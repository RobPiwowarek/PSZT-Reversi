package game.ai;

import java.security.SecureRandom;

public class Zobrist {
    private long zArray[][][];
    private long zBlackMove;
    private short size;

    public Zobrist(short size) {
        this.size = size;
        initArray();
    }

    public static long random64() {
        SecureRandom random = new SecureRandom();
        return random.nextLong();
    }

    public void initArray() {
        zArray = new long[2][size][size];
        for(int color = 0; color < 2; ++color) {
            for(int y = 0; y < size; ++y) {
                for(int x = 0; x < size; ++x) {
                    zArray[color][y][x] = random64();
                }
            }
        }
        zBlackMove = random64();
    }

    public long getGameKey(Game game) {
        long key = 0;
        int pawnColor;
        for(int y = 0; y < size; ++y) {
            for(int x = 0; x < size; ++x) {
                pawnColor = game.getPawnAsInt(x,y);
                if(pawnColor == 1) {
                    key ^= zArray[0][y][x];
                }
                else if(pawnColor == -1) {
                    key ^= zArray[1][y][x];
                }
            }
        }
        key ^= zBlackMove; // black always starts
        return key;
    }

    public long getUpdatedKey(Game game, long oldKey, int x, int y, int newColor, boolean changePlayer) {
        long newKey = oldKey;
        if(changePlayer) {
            newKey ^= zBlackMove;
        }
        if(x == -1 && y == -1) {
            return newKey;
        }
        int oldColor = game.getPawnAsInt(x,y);
        if(oldColor == newColor) {
            return newKey;
        }
        // xor out old position
        if(oldColor == 1) {
            newKey ^= zArray[0][y][x];
        }
        else if(oldColor == -1) {
            newKey ^= zArray[1][y][x];
        }
        // xor in new position
        if(newColor == 1) {
            newKey ^= zArray[0][y][x];
        }
        else if(newColor == -1) {
            newKey ^= zArray[1][y][x];
        }
        return newKey;
    }

}
