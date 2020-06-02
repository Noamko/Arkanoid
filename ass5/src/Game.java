import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Game class.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    
    /**
     * Game constructor.
     */
    public Game() {
        gui = new GUI("Arkanoid", Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    /**
     * add a Collidable to the game.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove a collidable from the game.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * add a sprite to the game.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove a  sprite from the game.
     * @param s Sprite
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
        remainingBalls.increase(2);
        ballRemover = new BallRemover(this, remainingBalls);

        score = new Counter();
        scoreIndicator = new ScoreIndicator(score);
        scoreTrackingListener = new ScoreTrackingListener(score);

        loadAss3Game(this, environment, gui);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.notifyAllTimePassed();
            this.sprites.drawAllOn(d);
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }

    public void loadAss3Game(Game game, GameEnvironment environment, GUI gui) {
        Block background = new Block(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        background.setColor(Color.DARK_GRAY);
        background.addToGame(game);
        game.removeCollidable(background);

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
                                * (j), 10 * Config.BLOCK_HEIGHT - Config.BLOCK_HEIGHT
                                * (i)), Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                blocksArr[i][j].setColor(rowColors[i]);
                blocksArr[i][j].addToGame(game);
                blocksArr[i][j].addHitListener(blockRemover);
                blocksArr[i][j].addHitListener(scoreTrackingListener);
            }
        }

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(280, Config.BALL_SPEED));
        ball.addToGame(game);

        Ball ball2 = new Ball(Config.BALL_2NDSTARTING_POSITION, Config.BALL_RADIUS, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(300, Config.BALL_SPEED));
        ball2.addToGame(game);

        Block wallLeft = new Block(
                new Point(0,  Config.WALL_SIZE), Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallLeft.addToGame(game);

        Block wallRight = new Block(
                new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE,  Config.WALL_SIZE),
                Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallRight.addToGame(game);

        Block wallTop = new Block(
                new Point(0,  20), Config.WINDOW_WIDTH , Config.WALL_SIZE);
        wallTop.addToGame(game);

        Block deathWall = new Block(
                new Point(0,  Config.WINDOW_HEIGHT), Config.WINDOW_WIDTH, Config.WALL_SIZE);
        deathWall.addHitListener(ballRemover);
        deathWall.addToGame(game);

        Paddle paddle = new Paddle(gui);
        paddle.addToGame(game);

        this.addSprite(scoreIndicator);
    }
}
