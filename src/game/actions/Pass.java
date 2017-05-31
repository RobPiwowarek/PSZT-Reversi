package game.actions;

import game.board.Point;

// ten ruch powinien byc mozliwy tylko kiedy nie da sie nic postawic
public class Pass extends Action {
    public Pass() {
        this.p = new Point(-1,-1);
    }
}