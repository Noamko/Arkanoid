package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import configuration.Config;
import ui.SpriteCollection;

public class CountdownAnimation implements Animation {

    private int timer;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection screen;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        timer = 3;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.screen = gameScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        numOfSeconds++;
        numOfSeconds /= Config.FPS;
        if (numOfSeconds % Config.FPS == 0) {
            timer--;
            d.drawText(Config.WINDOW_WIDTH / 2, 200,String.valueOf(timer),20);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
