package game.ai;

import game.actions.Action;

public class Transposition {
    public int getDepth() {
        return depth;
    }

    public TranspositionFlag getFlag() {
        return flag;
    }

    public Action getMove() {
        return move;
    }

    public int getValue() {
        return value;
    }

    public long getKey() {
        return key;
    }

    private long key;
    private int depth;
    private TranspositionFlag flag;
    private Action move;
    private int value;

    public Transposition(long key, int depth, TranspositionFlag flag, Action move, int value) {
        this.key = key;
        this.depth = depth;
        this.flag = flag;
        this.move = move;
        this.value = value;
    }
}
