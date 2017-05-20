package mvc;

import game.actions.Action;
import game.ai.AlphaBeta;
import game.board.Point;

public class AIController extends PlayerController {

    final int DEPTH = 6;

    @Override
    public boolean makeMove() {

        // TODO: tutaj kod ktory wyprodukuje move'a/point'a ktory zostanie wpakowany do odpowiedniej metody controllera
        // TODO: albo playerMove z networkcontrollera albo move() z local controllera (nazwy mozna ujednolicic potem

        AlphaBeta algorithm = new AlphaBeta(controller.getModel(), DEPTH);

        Action action = algorithm.play();

        controller.move(action.getPoint());

        return false;
    }
}
