package gamelogic;

import biuoop.GUI;
import biuoop.Sleeper;
import configuration.Config;
import objects.Paddle;

/**
 * @author Noam Koren 308192871 ass3
 */
public class Ass5Game {
    /**
     * main function to run the game.
     * creates a game instance initialize it and run
     * @param args (String[])
     */
    public static void main(String[] args) {
        // Create the GUI using buioop's implementation.
        GUI gui = new GUI(Config.WINDOW_TITLE, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        
        // Create and initialize a the game screen.
        Game game = new Game(gui.getKeyboardSensor(), new AnimationRunner(gui, 60, new Sleeper()));

        // Run the game.
        game.run();
        gui.close();
    }
}
