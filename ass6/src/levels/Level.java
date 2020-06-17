package levels;

import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import objects.Block;
import objects.Paddle;
import ui.Sprite;
import vector.Velocity;

import java.util.List;

public class Level implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }

    @Override
    public void load(GameLevel gl) {

    }

    @Override
    public Paddle getPaddle() {
        return null;
    }
}
