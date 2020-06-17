package gamelogic;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import scoresystem.Counter;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, score);
            level.initialize();

            while (level.blocksLeft() > 0 && level.ballsLeft() > 0) {
                level.run();
            }
            if(level.ballsLeft() == 0) {
                animationRunner.run(new EndScreen(false, score.getValue(),keyboardSensor));
            }
        }
        animationRunner.run(new EndScreen(true, score.getValue(),keyboardSensor));
    }
}
