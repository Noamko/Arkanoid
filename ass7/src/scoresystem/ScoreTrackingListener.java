package scoresystem;

import collision.HitListener;
import objects.Ball;
import objects.Block;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * keep track of the current player score.
     * @param c scoresystem.Counter.
     */
    public ScoreTrackingListener(Counter c) {
        this.currentScore = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
