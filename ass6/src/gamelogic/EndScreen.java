package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private final boolean gameCleared;
    private final int score;
    private boolean stop = false;

    public EndScreen(boolean win, int score){
        this.gameCleared = win;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.gameCleared) {
            d.drawText(10, d.getHeight() / 2, "You Win!  Your score is " + String.valueOf(score), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over Your score is " + String.valueOf(score), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
