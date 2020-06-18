package ui;

import biuoop.DrawSurface;
import geometry.Point;
import java.awt.Color;

/**
 * Circle class.
 */
public class Circle implements Sprite {
    private int radius;
    private Color stroke;
    private Color fill;
    private Point center;
    private boolean shouldfill = false;
    private boolean drawStroke = false;

    /**
     * constuctor.
     * @param center Point
     * @param r int
     */
    public Circle(Point center, int r) {
        this.radius = r;
        this.center = center;
    }

    /**
     * Sets the stroke color of the Circle.
     * @param c Color
     */
    public void setStroke(Color c) {
        this.drawStroke = true;
        this.stroke = c;
    }

    /**
     * set the fill color of the Circle.
     * @param c Color
     */
    public void fillCircle(Color c) {
        this.shouldfill = true;
        this.fill = c;
    }

    @Override
    /**
     * draw the circle on a given drawsurface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        if (drawStroke) {
            d.setColor(this.stroke);
            d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
        if (shouldfill) {
            d.setColor(this.fill);
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
    }

    @Override
    /**
     * time passed.
     */
    public void timePassed() {

    }
}
