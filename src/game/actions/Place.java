package game.actions;

import game.board.Point;

public class Place extends Action {
    public Place(int x, int y) {
        this.p = new Point(x, y);
    }
}