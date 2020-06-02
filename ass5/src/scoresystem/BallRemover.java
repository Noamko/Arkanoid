package scoresystem;

import collision.HitListener;
import gamelogic.Game;
import objects.Block;
import objects.Ball;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * this class is in charge of removing and counting how many balls are in the game.
     * @param game gamelogic.Game
     * @param c scoresystem.Counter
     */
    public BallRemover(Game game, Counter c) {
        this.game = game;
        this.remainingBalls = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
