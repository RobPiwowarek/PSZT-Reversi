package game.actions;

import game.board.Point;

// ten ruch powinien byc mozliwy tylko kiedy nie da sie nic postawic
public class Pass implements Action {
    private Point p;
    public Pass() {
        this.p = new Point(-1,-1);
    }
    public Point getPoint() {
        return this.p;
    }
}