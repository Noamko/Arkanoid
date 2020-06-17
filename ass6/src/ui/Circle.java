package ui;

import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;

public class Circle implements Sprite {
    private int radius;
    private Color stroke;
    private Color fill;
    private Point center;
    private boolean shouldfill = false;
    private boolean drawStroke = false;

    public Circle(Point center, int r) {
        this.radius = r;
        this.center = center;
    }

    public void setStroke(Color c) {
        this.drawStroke = true;
        this.stroke = c;
    }
    public void fillCircle(Color c) {
        this.shouldfill = true;
        this.fill = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if(drawStroke) {
            d.setColor(this.stroke);
            d.drawCircle((int) this.center.getX(), (int) this.center.getY(),this.radius);
        }
        if(shouldfill) {
            d.setColor(this.fill);
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(),this.radius);
        }
    }

    @Override
    public void timePassed() {

    }
}
