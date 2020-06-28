package gamelogic;

import animation.Animation;
import biuoop.DrawSurface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShowHighScoresAnimation implements Animation {

    private boolean stop;
    private String scoreText;
    public ShowHighScoresAnimation() {
        File f = new File("highscores.txt");
        try {
            if(f.exists()) {
                FileReader fr = new FileReader(f);
                char[] fileContent = new char[1024];
                fr.read(fileContent);
                fr.close();
                scoreText = String.valueOf(fileContent);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stop = false;
    }
    /**
     * run a single frame of the animation.
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, scoreText, 32);
    }

    /**
     * checks if the animation should stop.
     *
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
