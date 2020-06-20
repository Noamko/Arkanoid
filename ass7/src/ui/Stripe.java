package ui;

import biuoop.DrawSurface;
import geometry.Line;
import java.awt.Color;

/**
 * Stripe class.
 */
public class Stripe implements Sprite {

    private Line line;
    private Color color;

    /**
     * constructor.
     * @param l Line
     * @param c Color
     */
    public Stripe(Line l, Color c) {
        this.line = l;
        this.color = c;
    }
    @Override
    /**
     * the the stripe on the given drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        this.line.drawOn(d);
    }

    /**
     * returns the line of the stripe object.
     * @return Line
     */
    public Line getLine() {
        return this.line;
    }

    @Override
    /**
     * time passed.
     */
    public void timePassed() {

    }
}
