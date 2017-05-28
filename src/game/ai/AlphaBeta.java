package game.ai;

import game.actions.Action;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// bardzo generic AI dla dowolnej gry
public class AlphaBeta {
    private Action chosenMove;
    private int depth;
    private Game game;
    private Heuristic heuristic;

    public AlphaBeta(int depth) {
        this.depth = depth;
        this.heuristic = new Heuristic();
    }

    public void setGame(Game game) {
        this.game = game;
        this.heuristic.setSize(game.getSize());
    }

    private class GameActionPair {
        public Game game;
        public Action action;

        public GameActionPair(Game game, Action action) {
            this.game = game;
            this.action = action;
        }
    }

    private double alphaBeta(Game game, int depth, int origDepth, double alpha, double beta, boolean aiTurn) {
        Collection<Action> possibleMoves;
        double a, b, score;

        // TODO - tablice transponowań
        if (depth == 0 || game.isOver()) {
            score = heuristic.getScoring(game);
            // dzieki temu wzorkowi AI bierze pod uwage ilosc ruchow wartosciujac sciezke
            // szybsza wygrana > wolniejsza wygrana
            // wolniejsza przegrana > szybsza przegrana
            return (score - 0.01 * depth * Math.signum(score));
        }

        possibleMoves = game.getPossibleMoves();
        a = alpha;
        b = beta;

        // initial sort by shallow heuristic value
        List<GameActionPair> cloneList = possibleMoves
                .stream()
                .map(m -> {
                    Game clone = game.clone();
                    clone.makeMove(m);
                    return new GameActionPair(clone, m);
                })
                .sorted(Comparator.comparingInt(c -> heuristic.getScoring(c.game)))
                .collect(Collectors.toList());

        if (!aiTurn) {
            for (GameActionPair cloneMove : cloneList) {
                score = alphaBeta(cloneMove.game, depth - 1, origDepth, a, b, true);
                if (score < b) {
                    b = score;
                }
                if (a >= b) break; // odcinamy branch alpha
            }
            return b;
        } else {
            for (GameActionPair cloneMove : cloneList) {
                score = alphaBeta(cloneMove.game, depth - 1, origDepth, a, b, false);
                if (score > a) {
                    a = score;
                    if (depth == origDepth) { // wrocilismy do root nodea
                        chosenMove = cloneMove.action;
                    }
                }
                if (a >= b) break; // odcinamy branch beta
            }
            return a;
        }
    }

    // timeConstraint - nanoseconds
    public Action play(long timeConstraint) {
        double inf = Double.POSITIVE_INFINITY;
        long startTime = System.nanoTime();
        chosenMove = null; // just in case
        // maybe zeby scislej trzymac sie time constraint mozna by
        // w oddzielnym threadzie odliczac czas i po przekroczeniu na chama wyrzucic chosenMove
        for (int d = 1; d < this.depth && System.nanoTime() - startTime < timeConstraint; ++d) {
            alphaBeta(game, d, d, -inf, inf, true); // chosenMove will update
        }
        return chosenMove;
    }
}
