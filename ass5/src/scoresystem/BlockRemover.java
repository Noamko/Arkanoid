package scoresystem;

import collision.HitListener;
import gamelogic.Game;
import objects.Ball;
import objects.Block;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * block remover is in charge of removing block from the game, as well as keeping count.
     * of the number of blocks that remain
     * @param game game
     * @param removedBlocks counter
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(game);
    }
}
