import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter score;

    public ScoreIndicator(Counter c) {
        this.score = c;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0,0,Config.SCORE_INDICATOR_WIDTH, Config.SCORE_INDICATOR_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(Config.SCORE_INDICATOR_WIDTH/2 -5, Config.SCORE_INDICATOR_HEIGHT /2 + 5,"Score: " + this.score.getValue(),16);
    }

    @Override
    public void timePassed() {

    }
}
