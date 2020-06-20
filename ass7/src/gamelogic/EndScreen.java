package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndScreen class.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private final boolean gameCleared;
    private final int score;
    private boolean stop = false;

    /**
     * constractur.
     * @param win bool
     * @param score int
     */
    public EndScreen(boolean win, int score) {
        this.gameCleared = win;
        this.score = score;
    }

    @Override
    /**
     * run a single frame of the animation.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.gameCleared) {
            d.drawText(10, d.getHeight() / 2, "You Win!  Your score is " + String.valueOf(score), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over Your score is " + String.valueOf(score), 32);
        }
    }

    @Override
    /**
     * checks if the animation should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return stop;
    }
}
