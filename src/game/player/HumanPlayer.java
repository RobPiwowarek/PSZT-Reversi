package game.player;

import game.board.Point;

public class HumanPlayer extends Player {

    private Point legalMove;

    public void checkMove(Point point) {
        if (controller.getModel().canPlace(point)){
            legalMove = point;
            makeMove();
        }
        else {
            // TODO: some info back to the player to know he fucked up??
        }
    }

    @Override
    public void makeMove() {
        controller.move(legalMove);
        legalMove = null;
    }
}
