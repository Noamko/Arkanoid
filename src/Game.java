import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Game class.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    
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
        sprites.getSprites().remove(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        Block background = new Block(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        background.setColor(Color.darkGray);
        background.addToGame(this);
        environment.removeCollidable(background);

        Levels level = new Levels();
        level.loadAss3Game(this, environment, gui);
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
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
