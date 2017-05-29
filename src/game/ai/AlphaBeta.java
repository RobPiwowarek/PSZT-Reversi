package game.ai;

import game.actions.Action;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// bardzo generic AI dla dowolnej gry
public class AlphaBeta {
    private volatile Action chosenMove;
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


    private class IterativeDeepening implements Runnable {
        @Override
        public void run() {
            double inf = Double.POSITIVE_INFINITY;
            for (int d = 1; d < depth; ++d) {
                alphaBeta(game, d, d, -inf, inf, true); // chosenMove will update
            }
        }

        private double alphaBeta(Game game, int depth, int origDepth, double alpha, double beta, boolean aiTurn) {
            Collection<Action> possibleMoves;
            double a, b, score;

            if(Thread.currentThread().isInterrupted()) {
                return 0;
            }

            // TODO - tablice transponowań
            if (depth == 0 || game.isOver()) {
                score = heuristic.getScoring(game);
                // give higher values to quicker wins and slower losses
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
                    if(Thread.currentThread().isInterrupted()) {
                        return 0;
                    }
                    if (score < b) {
                        b = score;
                    }
                    if (a >= b) break; // alpha cut off
                }
                return b;
            } else {
                for (GameActionPair cloneMove : cloneList) {
                    score = alphaBeta(cloneMove.game, depth - 1, origDepth, a, b, false);
                    if(Thread.currentThread().isInterrupted()) {
                        return 0;
                    }
                    if (score > a) {
                        a = score;
                        if (depth == origDepth) { // back to root node
                            chosenMove = cloneMove.action;
                        }
                    }
                    if (a >= b) break; // beta cut off
                }
                return a;
            }
        }
    }

    // timeConstraint - nanoseconds
    public Action play(long timeConstraint) {
        final ExecutorService es = Executors.newSingleThreadExecutor();
        boolean result = false;
        chosenMove = null;
        es.execute(new IterativeDeepening());
        es.shutdown(); //maybe unnecessary
        try {
            result = es.awaitTermination(timeConstraint, TimeUnit.NANOSECONDS);
        }
        catch(InterruptedException e) {
        }
        if(!result) {
            es.shutdownNow(); //interrupt thread
        }
        return chosenMove;
    }
}
