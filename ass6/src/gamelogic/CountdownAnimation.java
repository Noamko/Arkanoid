package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import configuration.Config;
import ui.Sprite;
import ui.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation {

    private int timer;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection screen;
    private boolean stop = false;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.screen = gameScreen;
        timer = 0;
        this.countFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        timer += 1;
        if(timer % Config.FPS == 0) {
            countFrom -= 1;
        }
        for (Sprite s : this.screen.getSprites()) {
            s.drawOn(d);
        }

        d.setColor(Color.WHITE);
        if (countFrom > 0) {
            d.drawText(Config.WINDOW_WIDTH/2, 40, String.valueOf(countFrom), 20);
        } else {
            d.drawText(Config.WINDOW_WIDTH/2, 40, "GO!", 20);
        }
        if (countFrom == -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
