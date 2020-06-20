package ui;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * ui.SpriteCollection Class.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();
    /**
     * add a new ui.Sprite to the collection.
     * @param s ui.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * removes the a given sprite from the collection.
     * @param s ui.Sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * return the sprite collection.
     * @return List
     */
    public List<Sprite> getSprites() {
        return  sprites;
    }
}