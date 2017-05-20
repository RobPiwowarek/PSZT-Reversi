package game.ai;

import game.actions.Action;

import java.util.Collection;

// bardzo generic AI dla dowolnej gry
public class AlphaBeta {
    private Action chosenMove;
    private int depth;
    private Game game;

    public AlphaBeta(Game game, int depth) {
        this.game = game;
        this.depth = depth;
    }

    private double alphaBeta(Game game, int depth, int origDepth, double alpha, double beta, boolean aiTurn) {
        Collection<Action> possibleMoves;
        Game gameClone;
        double a, b, score;

        // TODO - tablice transponowań
        if (depth == 0 || game.isOver()) {
            score = game.getScoring();
            // dzieki temu wzorkowi AI bierze pod uwage ilosc ruchow wartosciujac sciezke
            // szybsza wygrana > wolniejsza wygrana
            // wolniejsza przegrana > szybsza przegrana
            return (score - 0.01 * depth * Math.signum(score));
        }

        possibleMoves = game.getPossibleMoves();
        a = alpha;
        b = beta;

        if (!aiTurn) {
            for (Action move : possibleMoves) {
                gameClone = game.clone();
                gameClone.makeMove(move);
                score = alphaBeta(gameClone, depth - 1, origDepth, a, b, true);
                if (score < b) {
                    b = score;
                }
                if (a >= b) break; // odcinamy branch alpha
            }
            return b;
        } else {
            for (Action move : possibleMoves) {
                gameClone = game.clone();
                gameClone.makeMove(move);
                score = alphaBeta(gameClone, depth - 1, origDepth, a, b, false);
                if (score > a) {
                    a = score;
                    if (depth == origDepth) { // wrocilismy do root nodea
                        chosenMove = move;
                    }
                }
                if (a >= b) break; // odcinamy branch beta
            }
            return a;
        }
    }

    public Action play() {
        double inf = Double.POSITIVE_INFINITY;
        alphaBeta(game, depth, depth, -inf, inf, true);
        return chosenMove;
    }
}
