package levels;

import configuration.Config;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import geometry.Line;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import ui.Background;
import ui.Sprite;
import vector.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * wide easy class.
 */
public class WideEasy implements LevelInformation {
    private final List<Velocity> ballVelocitys;
    private final List<Block> blocks;
    private final Paddle paddle;
    private final Background bg;
    private final int numberOfBalls = 10;
    private final int rows = 15;

    /**
     * constrcutor.
     */
    public WideEasy() {
        blocks = new ArrayList<>();
        ballVelocitys = new ArrayList<>();
        bg = new WEBackground();
        paddle = new Paddle();
    }

    @Override
    /**
     * return the amount of balls in the level.
     * @return int
     */
    public int numberOfBalls() {
        return ballVelocitys.size();
    }

    @Override
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List
     */
    public List<Velocity> initialBallVelocities() {
        return ballVelocitys;
    }

    @Override
    /**
     * return the paddle speed.
     * @return int
     */
    public int paddleSpeed() {
        return Config.PADDLE_SPEED;
    }

    @Override
    /**
     * return the paddle width.
     * @return int
     */
    public int paddleWidth() {
        return paddle.getWidth();
    }

    @Override
    /**
     * the level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        return bg;
    }

    @Override
    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return List
     */
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return int
     */
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    @Override
    /**
     * load the level components.
     * @param gl Gamelevel
     */
    public void load(GameLevel gl) {
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        for (int i = 0; i < rows; i++) {
                Block b = new Block(new Point(Config.WALL_SIZE + Config.BLOCK_WIDTH * i, 270),
                        Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                b.setColor(colors[i / 3]);
                blocks.add(b);
        }

        Random rand = new Random();
        for (int i = 0; i < numberOfBalls; i++) {
            int relativeX = Config.WINDOW_WIDTH / 2;
            int relativeY = Config.WINDOW_HEIGHT / 2;
            Velocity v = Velocity.fromAngleAndSpeed(
                    Config.DIRACTION_UP + rand.nextInt(10),
                    Config.BALL_SPEED + rand.nextInt(2));
            if (i < 5) {
                Ball b = new Ball(new Point(
                        relativeX / 1.1 - i * 30, relativeY + i * 30), 5, gl.getEnvironment());
                b.setVelocity(v);
                b.addToGame(gl);
            } else {
                Ball b = new Ball(new Point(
                        relativeX * 1.1 + (i - 5) * 30, relativeY + (i - 5) * 30), 5, gl.getEnvironment());
                b.setVelocity(v);
                b.addToGame(gl);
            }
            ballVelocitys.add(v);
        }
        paddle.setWidth(600);
    }

    @Override
    /**
     * get the paddle of the level.
     * @return Paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }
}

/**
 * background class.
 */
class WEBackground extends Background  {
    /**
     * final four background.
     */
    public WEBackground() {
        //Note about these magic numbers**///////////////////////////////////////////
        //these numbers are just to place the sprite on the screen to create drawings
        //put each one in a variable just so it wont be a "magic number is useless
        //changing these values wont change the game play it will just change the background
        //so i found it useless to change each background pixel values as below
        //and store them in const variables
        //HAPPY CHECKING :)
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.decode("#8FB7C1"));
        //Sun
        Point sunCenter = new Point(Config.WINDOW_WIDTH / 4, Config.WINDOW_HEIGHT / 4);
        for (int i = 1; i < 1000; i += 20) {
            addLine(new Line(sunCenter, new Point(i, 270)), Color.ORANGE);
        }
        addCircle(sunCenter, 90, Color.decode("#F4FFB9"));
        addCircle(sunCenter, 80, Color.decode("#FFFF55"));
        addCircle(sunCenter, 70, Color.decode("#FFD30A"));
    }
}
