package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;

public class KeyPressStoppableAnimation implements Animation {
    
    @Override
    public void doOneFrame(DrawSurface d) {

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
