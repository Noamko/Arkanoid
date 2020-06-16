package gamelogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import configuration.Config;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import scoresystem.ScoreIndicator;
import scoresystem.BallRemover;
import scoresystem.BlockRemover;
import scoresystem.Counter;
import scoresystem.ScoreTrackingListener;
import ui.SpriteCollection;
import ui.Sprite;
import ui.TopBar;
import vector.Velocity;
import java.awt.Color;
import animation.Animation;
import animation.AnimationRunner;

/**
 * gamelogic.Game class.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation level;
    /**
     * gamelogic Game constructor.
     */
    public GameLevel(LevelInformation li) {
        GUI gui = new GUI("Arkanoid", Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        runner = new AnimationRunner(gui, Config.FPS);
        keyboard = gui.getKeyboardSensor();
        this.level = li;
        this.lives = new Counter();
        this.lives.setValue(7);
    }

    /**
     * add a collision.Collidable to the game.
     * @param c collision.Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove a collidable from the game.
     * @param c collision.Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * add a sprite to the game.
     * @param s ui.Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove a  sprite from the game.
     * @param s ui.Sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        remainingBlocks = new Counter();
        blockRemover = new BlockRemover(this, remainingBlocks);

        remainingBalls = new Counter();
        ballRemover = new BallRemover(this, remainingBalls);


        level.load(this, this.keyboard);
        for (Block block : level.blocks()) {
            block.addHitListener(blockRemover);
        }

        Block wallLeft = new Block(
                new Point(0,  Config.WALL_SIZE), Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallLeft.addToGame(this);

        Block wallRight = new Block(
                new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE,  Config.WALL_SIZE),
                Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallRight.addToGame(this);

        Block wallTop = new Block(
                new Point(0,  20), Config.WINDOW_WIDTH , Config.WALL_SIZE);
        wallTop.addToGame(this);

        Block deathWall = new Block(
                new Point(0,  Config.WINDOW_HEIGHT), Config.WINDOW_WIDTH, Config.WALL_SIZE);
        deathWall.addHitListener(ballRemover);
        deathWall.addToGame(this);

        score = new Counter();
        scoreIndicator = new ScoreIndicator(score);
        scoreTrackingListener = new ScoreTrackingListener(score);
        TopBar tb = new TopBar(scoreIndicator,level.levelName(),lives);
        this.addSprite(tb);

        remainingBlocks.setValue(level.numberOfBlocksToRemove());
        remainingBalls.setValue(level.numberOfBalls());
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * loads ass5 game.
     */
    public void loadAss5Game() {
        Block background = new Block(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        background.setColor(Color.DARK_GRAY);
        background.addToGame(this);
        this.removeCollidable(background);

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
                remainingBlocks.increase(1);
                blocksArr[i][j] = new Block(
                        new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE - Config.BLOCK_WIDTH
                                * (j +1), 10 * Config.BLOCK_HEIGHT - Config.BLOCK_HEIGHT
                                * (i +1)), Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                blocksArr[i][j].setColor(rowColors[i]);
                blocksArr[i][j].addToGame(this);
                blocksArr[i][j].addHitListener(blockRemover);
                blocksArr[i][j].addHitListener(scoreTrackingListener);
            }
        }

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(280, Config.BALL_SPEED));
        ball.addToGame(this);

        Ball ball2 = new Ball(Config.BALL_2ND_STARTING_POSITION, Config.BALL_RADIUS, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(300, Config.BALL_SPEED));
        ball2.addToGame(this);

        Block wallLeft = new Block(
                new Point(0,  Config.WALL_SIZE), Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallLeft.addToGame(this);

        Block wallRight = new Block(
                new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE,  Config.WALL_SIZE),
                Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallRight.addToGame(this);

        Block wallTop = new Block(
                new Point(0,  20), Config.WINDOW_WIDTH , Config.WALL_SIZE);
        wallTop.addToGame(this);

        Block deathWall = new Block(
                new Point(0,  Config.WINDOW_HEIGHT), Config.WINDOW_WIDTH, Config.WALL_SIZE);
        deathWall.addHitListener(ballRemover);
        deathWall.addToGame(this);

        Paddle paddle = new Paddle(this.keyboard);
        paddle.addToGame(this);

        this.addSprite(scoreIndicator);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
            }
            this.running = false;
        }
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
