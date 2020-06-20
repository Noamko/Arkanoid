package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;

/**
 * PauseScreen class.
 * shows pause screen
 */
public class PauseScreen implements Animation {

    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    /**
     * run a single frame of the animation.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * checks if the animation should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
