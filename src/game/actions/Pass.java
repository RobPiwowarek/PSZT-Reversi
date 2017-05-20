package game.actions;

import game.board.Point;

// ten ruch powinien byc mozliwy tylko kiedy nie da sie nic postawic
public class Pass implements Action {
    public Point getPoint() {
        return null;
    }
}