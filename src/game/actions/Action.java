package game.actions;

import game.board.Point;

public class Action {
    protected Point p;
    public Point getPoint() { return p; }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Action)) {
            return false;
        }

        Action that = (Action) other;

        return this.p.getX() == that.p.getX() && this.p.getY() == that.p.getY();
    }
}
