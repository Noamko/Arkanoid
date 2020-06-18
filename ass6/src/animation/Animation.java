package animation;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * run a single frame of the animation.
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * checks if the animation should stop.
     * @return boolean
     */
    boolean shouldStop();
}
