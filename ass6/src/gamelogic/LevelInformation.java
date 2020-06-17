package gamelogic;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import objects.Block;
import objects.Paddle;
import scoresystem.BallRemover;
import scoresystem.BlockRemover;
import ui.Sprite;
import vector.Velocity;

import java.util.List;

public interface LevelInformation {
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    String levelName();
    // Returns a sprite with the background of the level
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();

    void load(GameLevel gl);

    Paddle getPaddle();
}
