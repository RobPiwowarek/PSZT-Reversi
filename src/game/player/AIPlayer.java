package game.player;

import game.actions.Action;
import game.ai.AlphaBeta;

import java.util.Collections;

public class AIPlayer extends Player {

    final int DEPTH = 6;

    @Override
    public void makeMove() {

        AlphaBeta algorithm = new AlphaBeta(controller.getModel(), DEPTH);

        Action action = algorithm.play();

        controller.move(action.getPoint());
    }
}
