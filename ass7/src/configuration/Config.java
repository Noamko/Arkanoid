package configuration;
import geometry.Point;
/**
 * This class is used to store all global constants definitions.
 * and settings
 */
public class Config {
    //IMPORTANT NOTE: java compiler is stupid so if you want to change staff around here,
    //you need to rebuild the project for the changes to take place.
    //this happens because the original file who uses this constants do not change.
    //so the compiler simply ignores them...
    /////////////////////////////////////////////////////////////////////////////////////
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WALL_SIZE = 25;
    public static final int BALL_RADIUS = 5;
    public static final int BALL_SPEED = 4;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int INVALID_SLOPE = -999;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_SPEED = 8;
    public static final Point BALL_STARTING_POSITION = new Point(WINDOW_WIDTH / 2,
            Config.WINDOW_HEIGHT - Config.PADDLE_HEIGHT);
    public static final int COLLISION_ERROR  = 1;
    public static final int SCORE_INDICATOR_HEIGHT = 20;
    public static final int SCORE_INDICATOR_WIDTH = WINDOW_WIDTH;
    public static final int FPS = 60;
    public static final int DIRACTION_DOWN = 180;
    public static final int DIRACTION_UP = 0;
    public static final int DIRACTION_LEFT = 90;
    public static final int DIRACTION_RIGHT = 270;
}
