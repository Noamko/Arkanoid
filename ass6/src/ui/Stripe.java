package ui;

import biuoop.DrawSurface;
import geometry.Line;

import java.awt.*;

public class Stripe implements Sprite {

    private Line line;
    private Color color;

    public Stripe(Line l, Color c) {
        this.line = l;
        this.color = c;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        this.line.drawOn(d);
    }

    public Line getLine() {
        return this.line;
    }

    @Override
    public void timePassed() {

    }
}
