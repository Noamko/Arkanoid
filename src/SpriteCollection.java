import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection Class.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * add a new Sprite to the collection.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
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