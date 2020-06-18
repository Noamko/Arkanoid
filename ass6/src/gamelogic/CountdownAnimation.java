package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import configuration.Config;
import ui.Sprite;
import ui.SpriteCollection;
import java.awt.Color;

/**
 * CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {

    private int timer;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection screen;
    private boolean stop = false;

    /**
     * constractur.
     * @param countFrom int
     * @param gameScreen SpriteCollection
     */
    public CountdownAnimation(int countFrom, SpriteCollection gameScreen) {
        this.screen = gameScreen;
        timer = 0;
        this.countFrom = countFrom;
    }

    @Override
    /**
     * run a single frame of the animation.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        timer += 1;
        //each time the number of frames is dividable by the fps we count  that exactly the one seconds had passed.
        if (timer % Config.FPS == 0) {
            countFrom -= 1;
            timer = 0;
        }
        for (Sprite s : this.screen.getSprites()) {
            s.drawOn(d);
        }

        d.setColor(Color.WHITE);
        if (countFrom > 0) {
            d.drawText(Config.WINDOW_WIDTH / 2, 80, String.valueOf(countFrom), 40);
        }
        if (countFrom == 0) {
            this.stop = true;
        }
    }


    @Override
    /**
     * checks if the animation should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return stop;
    }
}
