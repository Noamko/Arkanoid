package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private final boolean gameCleared;
    private final int score;
    private boolean stop = false;

    public EndScreen(boolean win, int score, KeyboardSensor ks){
        this.gameCleared = win;
        this.score = score;
        this.keyboard = ks;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.gameCleared) {
            //Win screen
            d.drawText(10, d.getHeight() / 2, "You Win!  Your score is " + String.valueOf(score), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over Your score is " + String.valueOf(score), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
