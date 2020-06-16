package configuration;
import geometry.Point;
/**
 * This class is used to store all global constants definitions.
 * and settings
 */
public class Config {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WALL_SIZE = 30;
    public static final int BALL_RADIUS = 5;
    public static final int BALL_SPEED = 6;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int INVALID_SLOPE = -999;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_SPEED = 8;
    public static final Point BALL_STARTING_POSITION = new Point(WINDOW_WIDTH / 2,
            Config.WINDOW_HEIGHT - Config.PADDLE_HEIGHT);
    public static final Point BALL_2ND_STARTING_POSITION = new Point(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3);
    public static final int COLLISION_ERROR  = 1;
    public static final int SCORE_INDICATOR_HEIGHT = 20;
    public static final int SCORE_INDICATOR_WIDTH = WINDOW_WIDTH;
    public static final int FPS = 60;
}
