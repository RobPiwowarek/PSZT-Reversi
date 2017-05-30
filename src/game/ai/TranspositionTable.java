package game.ai;

import java.util.HashMap;

public class TranspositionTable extends HashMap<Integer,Transposition> {
    private int size;

    public TranspositionTable(int size) {
        super(size);
        this.size = size;
    }

    private int getHash(long zobristKey) {
        return (int)(zobristKey % size);
    }

    public Transposition getTransposition(Game game) {
        return super.get(getHash(game.getZobristKey()));
    }

    public void putTransposition(Game game, Transposition transposition) {
        super.put(getHash(game.getZobristKey()), transposition);
    }
}
