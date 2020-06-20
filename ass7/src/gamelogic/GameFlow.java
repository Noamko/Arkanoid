package gamelogic;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import scoresystem.Counter;

import java.util.List;

/**
 * Gameflow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Gameflow constractur.
     * @param ar Animationrunner
     * @param ks Kyeboardsensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * run all the levels from a given level list.
     * @param levels Levelinformation list
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, score);
            level.initialize();

            while (level.blocksLeft() > 0 && level.ballsLeft() > 0) {
                level.run();
            }

            if (level.ballsLeft() == 0) {
                animationRunner.run(new KeyPressStoppableAnimation(
                        keyboardSensor, "space", new EndScreen(false, this.score.getValue())));
                return;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(
                keyboardSensor, "space", new EndScreen(true, this.score.getValue())));
    }
}
