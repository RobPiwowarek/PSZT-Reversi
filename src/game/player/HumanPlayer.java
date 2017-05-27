package game.player;

import game.board.Point;

public class HumanPlayer extends Player {

    private Point legalMove;

    public boolean checkMove(Point point) {
        if (controller.canPlace(point)){
            legalMove = point;
            makeMove();
            return true;
        }
        else {
            // TODO: some info back to the player to know he fucked up??
            return false;
        }
    }

    @Override
    public void makeMove() {
        controller.move(legalMove);
        legalMove = null;
    }
}
