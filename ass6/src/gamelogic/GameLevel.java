package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import configuration.Config;
import geometry.Point;
import objects.Block;
import scoresystem.ScoreIndicator;
import scoresystem.BallRemover;
import scoresystem.BlockRemover;
import scoresystem.Counter;
import scoresystem.ScoreTrackingListener;
import ui.SpriteCollection;
import ui.Sprite;
import ui.TopBar;
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
    public boolean gameCleared = false;
    /**
     * gamelogic Game constructor.
     */
    public GameLevel(LevelInformation li, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        runner = ar;
        keyboard = ks;
        this.level = li;
        this.lives = new Counter();
        this.lives.setValue(7);
        this.score = score;
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

        scoreIndicator = new ScoreIndicator(score);
        scoreTrackingListener = new ScoreTrackingListener(score);

        this.addSprite(level.getBackground());
        level.load(this);

        for (Block block : level.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        remainingBlocks.setValue(level.numberOfBlocksToRemove());
        remainingBalls.setValue(level.numberOfBalls());
        level.getPaddle().addToGame(this);

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

        TopBar tb = new TopBar(scoreIndicator,level.levelName(),lives);
        this.addSprite(tb);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        else if(this.keyboard.isPressed("left")){
            this.level.getPaddle().moveLeft();
        }

        else if(this.keyboard.isPressed("right")){
            this.level.getPaddle().moveRight();

        }
        else {
            this.level.getPaddle().stopMoving();
        }

        if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                this.running = false;
            }
        }
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    public int blocksLeft() {
        return remainingBlocks.getValue();
    }

    public int ballsLeft() {
        return  remainingBalls.getValue();
    }
}
