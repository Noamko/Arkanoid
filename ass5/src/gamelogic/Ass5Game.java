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

        // Create and initialize a new game screen.
        Game game = new Game();
        game.initialize();

        // Paddle's interface defines that it must get GUI as an argument.
        // Create it here and provide it to the game object to avoid the need
        // for Game to know GUI.
        Paddle paddle = new Paddle(gui);
        paddle.addToGame(game);

        // Create the main runner using buioop's implementations and run the game.
        AnimationRunner runner = new AnimationRunner(gui, 60, new Sleeper());
        runner.run(game);
    }
}
