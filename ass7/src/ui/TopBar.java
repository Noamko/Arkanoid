package ui;

import biuoop.DrawSurface;
import configuration.Config;
import scoresystem.Counter;
import scoresystem.ScoreIndicator;

import java.awt.Color;

/**
 * Topbar class.
 */
public class TopBar implements Sprite {
    private ScoreIndicator scoreIndicator;
    private String levelName;
    private Counter lives;

    /**
     * constractor.
     * @param si Scoreindicator
     * @param levelName String
     * @param lives Counter
     */
    public TopBar(ScoreIndicator si, String levelName, Counter lives) {
        this.scoreIndicator = si;
        this.levelName = levelName;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, Config.SCORE_INDICATOR_WIDTH, Config.SCORE_INDICATOR_HEIGHT);
        scoreIndicator.drawOn(d);

        d.setColor(Color.BLACK);
        d.drawText(Config.SCORE_INDICATOR_WIDTH / 2 + 150, Config.SCORE_INDICATOR_HEIGHT / 2 + 5,
                "Level Name: " + this.levelName , 16);

        d.drawText(Config.SCORE_INDICATOR_WIDTH / 2 - 250, Config.SCORE_INDICATOR_HEIGHT / 2 + 5,
                "Lives: " + this.lives.getValue() , 16);

    }

    @Override
    public void timePassed() {

    }
}
