package gamelogic;

import objects.Block;
import objects.Paddle;
import ui.Sprite;
import vector.Velocity;
import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * return the amount of balls in the level.
     * @return int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the paddle speed.
     * @return int
     */
    int paddleSpeed();

    /**
     * return the paddle width.
     * @return int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return String
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return List
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return int
     */
    int numberOfBlocksToRemove();

    /**
     * load the level components.
     * @param gl Gamelevel
     */
    void load(GameLevel gl);

    /**
     * get the paddle of the level.
     * @return Paddle
     */
    Paddle getPaddle();
}
