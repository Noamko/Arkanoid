package scoresystem;

import collision.HitListener;
import gamelogic.GameLevel;
import objects.Ball;
import objects.Block;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * block remover is in charge of removing block from the game, as well as keeping count.
     * of the number of blocks that remain
     * @param gameLevel game
     * @param removedBlocks counter
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
    }
}
