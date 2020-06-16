import gamelogic.GameLevel;
import levels.DirectHit;

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
        GameLevel gameLevel = new GameLevel(new DirectHit());
        gameLevel.initialize();
        gameLevel.run();
    }
}
