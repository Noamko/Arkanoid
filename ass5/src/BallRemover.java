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
     * @param game Game
     * @param c Counter
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
