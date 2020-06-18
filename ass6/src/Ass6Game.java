import animation.AnimationRunner;
import biuoop.GUI;
import configuration.Config;
import gamelogic.GameFlow;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Koren
 * 308192871
 * ass3
 */
public class Ass6Game {
    /**
     * main function to run the game.
     * creates a game instance initialize it and run
     * @param args (String[])
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, Config.FPS);
        List<LevelInformation> lvls = new ArrayList<>();
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());

        System.out.println(args.length);
        if (args.length == 0) {
//            lvls.add(new DirectHit());
//            lvls.add(new WideEasy());
//            lvls.add(new Green3());
            lvls.add(new FinalFour());
        }
        gameFlow.runLevels(lvls);
        gui.close();
    }
}
