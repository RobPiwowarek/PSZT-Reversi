package game.actions;

import game.board.Point;

public class Place implements Action {
    private Point point;

    public Place(int x, int y) {
        this.point = new Point(x, y);
    }

    public Point getPoint() {
        return point;
    }
}