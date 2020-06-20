package ui;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import objects.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Background class.
 */
public class Background implements Sprite {
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public Background() {
        this.sprites = new ArrayList<>();
    }

    /**
     * adds a circle to the background.
     * @param p Point
     * @param r int
     * @param c Color
     */
    public void addCircle(Point p, int r, Color c) {
        Circle circle = new Circle(p, r);
        circle.fillCircle(c);
        this.sprites.add(circle);
    }

    /**
     * adds a circle to the background.
     * @param p Point
     * @param r int
     * @param c Color
     * @param s Color
     */
    public void addCircle(Point p, int r, Color c, Color s) {
        Circle circle = new Circle(p, r);
        circle.fillCircle(c);
        circle.setStroke(s);
        this.sprites.add(circle);
    }

    /**
     * adds a circle to the background.
     * @param p Point
     * @param r int
     * @param s Color
     * @param i bool
     */
    public void addCircle(Point p, int r, Color s, boolean i) {
        Circle circle = new Circle(p, r);
        circle.setStroke(s);
        this.sprites.add(circle);
    }

    /**
     * adds a block the the background.
     * @param p Point
     * @param w int
     * @param h int
     * @param c Color
     */
    public void addBlock(Point p, int w, int h, Color c) {
        Block block = new Block(p, w, h);
        block.setColor(c);
        this.sprites.add(block);
    }

    /**
     * adds a line the the background.
     * @param l Line
     * @param c Color
     */
    public void addLine(Line l, Color c) {
        Stripe stripe = new Stripe(l, c);
        this.sprites.add(stripe);
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }
}
