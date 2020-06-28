package gamelogic;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import scoresystem.Counter;

import java.io.*;

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
                updateScoreFile(score);
                return;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(
                keyboardSensor, "space", new EndScreen(true, this.score.getValue())));
        updateScoreFile(score);
    }

    private void updateScoreFile(Counter s) {
        try {
            File f = new File("highscores.txt");
            FileWriter fw;
            FileReader fr = new FileReader("highscores.txt");
            char[] text = new char[1024];
            if (f.exists()) {
                fr.read(text);
                fr.close();
                String decodedText = String.valueOf(text).replaceAll("\\p{Blank}","").trim();
                boolean shouldAppend = (decodedText.length() == 0 || decodedText.startsWith("Thehighestscoresofaris:"));
                if (!shouldAppend) {
                    fw = new FileWriter("highscores.txt");
                    fw.write("The highest score so far is: " + s.getValue());
                    fw.close();
                } else {
                    int start = decodedText.indexOf(':') + 1;
                    int currentScore = Integer.decode(decodedText.substring(start));
                    if (currentScore < s.getValue()) {
                        fw = new FileWriter("highscores.txt");
                        fw.write("The highest score so far is: " + s.getValue());
                        fw.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
