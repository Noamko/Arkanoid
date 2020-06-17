package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop = false;
    private boolean animationStarted = false;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if(!(!animationStarted && this.keyboard.isPressed(this.key))) {
            animation.doOneFrame(d);
            animationStarted = true;
            if (this.keyboard.isPressed(this.key)) {
                this.stop = true;
            }
        } else {
            animation.doOneFrame(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
