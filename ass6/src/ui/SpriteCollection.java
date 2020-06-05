package ui;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * ui.SpriteCollection Class.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private  Sprite spriteToRemove;

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
        spriteToRemove = s;
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        if (spriteToRemove != null) {
            sprites.remove(spriteToRemove);
        }

        for (Sprite s : sprites) {
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