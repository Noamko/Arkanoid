package levels;

import configuration.Config;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import ui.Background;
import ui.Sprite;
import vector.Velocity;

import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * green3 class.
 */
public class Green3 implements LevelInformation {
    private final List<Block> blocks;
    private final Paddle paddle;
    private final Background bg;
    private final List<Velocity> ballVelocitys;
    private final int numberOfBalls = 2;

    /**
     * constructor.
     */
    public Green3() {
        bg = new G3Background();
        blocks = new ArrayList<>();
        paddle = new Paddle();
        ballVelocitys = new ArrayList<>();

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
        return Config.PADDLE_WIDTH;
    }

    @Override
    /**
     * the level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Green 3";
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
        return 40;
    }

    @Override
    /**
     * load the level components.
     * @param gl Gamelevel
     */
    public void load(GameLevel gl) {
        Block[] grayBlocks = new Block[12];
        Block[] redBlocks = new Block[11];
        Block[] yellowBlocks = new Block[10];
        Block[] blueBlocks = new Block[9];
        Block[] pinkBlocks = new Block[8];
        Block[] greenBlocks = new Block[7];

        Block[][] blocksArr = {greenBlocks, pinkBlocks, blueBlocks, yellowBlocks, redBlocks, grayBlocks};
        Color[] rowColors = {Color.GREEN, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY};
        for (int i = 0; i < blocksArr.length; i++) {
            for (int j = 0; j < blocksArr[i].length; j++) {
                blocksArr[i][j] = new Block(
                        new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE - Config.BLOCK_WIDTH
                                * (j + 1), 10 * Config.BLOCK_HEIGHT - Config.BLOCK_HEIGHT

                                * (i + 1)), Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                blocksArr[i][j].setColor(rowColors[i]);
                blocks.add(blocksArr[i][j]);
            }
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(
                    Config.DIRACTION_UP + rand.nextInt(10),
                    Config.BALL_SPEED + rand.nextInt(2));
            Ball b = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
            b.setVelocity(v);
            b.addToGame(gl);
            ballVelocitys.add(v);
        }
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
class G3Background extends Background {
    /**
     * green 3 background.
     */
    public G3Background() {
        //Note about these magic numbers**///////////////////////////////////////////
        //these numbers are just to place the sprite on the screen to create drawings
        //put each one in a variable just so it wont be a "magic number is useless
        //changing these values wont change the game play it will just change the background
        //so i found it useless to change each background pixel values as below
        //and store them in const variables
        //HAPPY CHECKING :)
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.decode("#009A47"));

        //Building
        int buildingheight = 260;
        addBlock(new Point(100, Config.WINDOW_HEIGHT - buildingheight), 170, buildingheight, Color.darkGray);
        for (int i = 0; i < 220; i += 30) {
            for (int j = 0; j < 150; j += 20) {
                addBlock(new Point(110 + j, Config.WINDOW_HEIGHT - buildingheight + 10 + i), 12, 25, Color.white);
            }
        }
        //Antena
        addBlock(new Point(165, Config.WINDOW_HEIGHT - buildingheight - 60), 30, 60, ColorUIResource.gray);
        addBlock(new Point(177, Config.WINDOW_HEIGHT - buildingheight - 180), 5, 120, ColorUIResource.gray);
        Point acenter = new Point(180, Config.WINDOW_HEIGHT - buildingheight - 180);
        addCircle(acenter, 10, Color.orange);
        addCircle(acenter, 5, Color.red);
        addCircle(acenter, 2, Color.white);
    }
}
