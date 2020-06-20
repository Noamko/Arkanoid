package ui;

import biuoop.DrawSurface;

/**
 * ui.Sprite Interface.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     *  notify the sprite that time has passed.
     */
    void timePassed();
}

