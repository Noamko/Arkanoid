import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Score indicator gui.
     * @param c Counter
     */
    public ScoreIndicator(Counter c) {
        this.score = c;
    }

    /**
     * Draw the score on the screen.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, Config.SCORE_INDICATOR_WIDTH, Config.SCORE_INDICATOR_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(Config.SCORE_INDICATOR_WIDTH / 2 - 5, Config.SCORE_INDICATOR_HEIGHT / 2 + 5,
                "Score: " + this.score.getValue(), 16);
    }

    @Override
    public void timePassed() {

    }
}
