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

/**
 * final four class.
 */
public class FinalFour implements LevelInformation {
    private List<Block> blocks;
    private Paddle paddle;
    private Background bg;

    /**
     * constractor.
     */
    public FinalFour() {
        blocks = new ArrayList<>();
        paddle = new Paddle();
        bg = new FFBackground();
    }

    @Override
    /**
     * return the amount of balls in the level.
     * @return int
     */
    public int numberOfBalls() {
        return 3;
    }

    @Override
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List
     */
    public List<Velocity> initialBallVelocities() {
        return null;
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
        return Config.PADDLE_WIDTH;
    }

    @Override
    /**
     * the level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Final Four";
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

        Color[] rowColors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(
                        new Point(Config.WALL_SIZE + (Config.BLOCK_WIDTH * j), 100 + Config.BLOCK_HEIGHT * i),
                        Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                        block.setColor(rowColors[i]);
                blocks.add(block);
            }
        }
        Ball b1 = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        Ball b2 = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        Ball b3 = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        b1.setVelocity(Velocity.fromAngleAndSpeed(0, Config.BALL_SPEED));
        b2.setVelocity(Velocity.fromAngleAndSpeed(10, Config.BALL_SPEED));
        b3.setVelocity(Velocity.fromAngleAndSpeed(15, Config.BALL_SPEED));
        b1.addToGame(gl);
        b2.addToGame(gl);
        b3.addToGame(gl);
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
class FFBackground extends Background {
    /**
     * final fouar background.
     */
    public FFBackground() {
        //Note about these magic numbers**///////////////////////////////////////////
        //these numbers are just to place the sprite on the screen to create drawings
        //put each one in a variable just so it wont be a "magic number is useless
        //changing these values wont change the game play it will just change the background
        //so i found it useless to change each background pixel values as below
        //and store them in const variables
        //HAPPY CHECKING :)
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.decode("#009CF0"));
        for (int i = 0; i < 80; i += 10) {
            addLine(new Line(550 + i, 380 + i, 450 + i, 800 + i), Color.WHITE);
            addLine(new Line(130 + i, 360 + i, 50 + i, 800 + i), Color.WHITE);
        }
        addCircle(new Point(550, 400), 30, Color.decode("#C0C0C0"));
        addCircle(new Point(570, 380), 35, Color.decode("#D0D0D0"));
        addCircle(new Point(591, 400), 40, Color.decode("#E6E6E6"));
        addCircle(new Point(610, 420), 35, Color.decode("#D2D2D2"));

        addCircle(new Point(150, 380), 30, Color.decode("#C0C0C0"));
        addCircle(new Point(170, 400), 35, Color.decode("#D0D0D0"));
        addCircle(new Point(191, 390), 40, Color.decode("#E6E6E6"));

    }

}
