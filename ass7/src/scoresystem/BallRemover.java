package scoresystem;

import collision.HitListener;
import gamelogic.GameLevel;
import objects.Block;
import objects.Ball;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * this class is in charge of removing and counting how many balls are in the game.
     * @param gameLevel gamelogic.Game
     * @param c scoresystem.Counter
     */
    public BallRemover(GameLevel gameLevel, Counter c) {
        this.gameLevel = gameLevel;
        this.remainingBalls = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
