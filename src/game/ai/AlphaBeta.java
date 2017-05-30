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
    private int chosenMoveDepth;

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

    private class ScoreActionPair {
        public int score;
        public Action action;

        public ScoreActionPair(int score, Action action) {
            this.score = score;
            this.action = action;
        }
    }


    private class IterativeDeepening implements Runnable {
        private Game gameClone;
        public IterativeDeepening(Game game) {
            this.gameClone = game.clone();
        }
        @Override
        public void run() {
            double inf = Double.POSITIVE_INFINITY;
            for (int d = 1; d < depth; ++d) {
                alphaBeta(gameClone, d, d, -inf, inf, true); // chosenMove will update
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
            List<Action> moveList = possibleMoves
                    .stream()
                    .map(m -> {
                        int s;
                        game.makeMove(m);
                        s = heuristic.getScoring(game);
                        game.undoLastMove();
                        return new ScoreActionPair(s, m);
                    })
                    .sorted((c1,c2) -> Integer.compare(c2.score, c1.score))
                    .map(sap -> sap.action)
                    .collect(Collectors.toList());

            if (!aiTurn) {
                for (Action move : moveList) {
                    game.makeMove(move);
                    score = alphaBeta(game, depth - 1, origDepth, a, b, true);
                    game.undoLastMove();
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
                for (Action move : moveList) {
                    game.makeMove(move);
                    score = alphaBeta(game, depth - 1, origDepth, a, b, false);
                    game.undoLastMove();
                    if(Thread.currentThread().isInterrupted()) {
                        return 0;
                    }
                    if (score > a) {
                        a = score;
                        if (depth == origDepth) { // back to root node
                            chosenMove = move;
                            chosenMoveDepth = depth;
                        }
                    }
                    if (a >= b) break; // beta cut off
                }
                return a;
            }
        }
    }

    // timeConstraint - milliseconds
    public Action play(long timeConstraint) {
        final ExecutorService es = Executors.newSingleThreadExecutor();
        boolean result = false;
        chosenMove = null;
        es.execute(new IterativeDeepening(game));
        es.shutdown(); //maybe unnecessary
        try {
            result = es.awaitTermination(timeConstraint, TimeUnit.MILLISECONDS);
            if(!result)
            {
                es.shutdownNow(); //interrupt thread
            }
        }
        catch(InterruptedException e) {
            es.shutdownNow();
        }

        System.out.println("AI move depth: " + chosenMoveDepth);
        return chosenMove;
    }
}
