package ui;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class Background implements Sprite {
    private List<Sprite> sprites;

    public Background() {
        this.sprites = new ArrayList<>();
    }

    public List<Sprite> getSprites() {
        return sprites;
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
