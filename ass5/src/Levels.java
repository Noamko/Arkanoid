import biuoop.GUI;

import java.awt.Color;

/**
 * Levels Class.
 */
public class Levels {
    /**
     * Load the ass3 Game Level.
     * @param game Game
     * @param environment GameEnvironment
     * @param gui Gui
     */
    public void loadAss3Game(Game game, GameEnvironment environment, GUI gui) {
        Block background = new Block(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        background.setColor(Color.DARK_GRAY);
        background.addToGame(game);
        game.removeCollidable(background);

        Block[] grayBlocks = new Block[12];
        Block[] redBlocks = new Block[11];
        Block[] yellowBlocks = new Block[10];
        Block[] blueBlocks = new Block[9];
        Block[] pinkBlocks = new Block[8];
        Block[] greenBlocks = new Block[7];

        Block[][] blocksArr = {greenBlocks, pinkBlocks, blueBlocks, yellowBlocks, redBlocks, grayBlocks};
        Color[] rowColors = {Color.GREEN, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY};
        for (int i = 0; i < blocksArr.length; i++) {
            for (int j = 0; j < blocksArr[i].length; j++) {
                blocksArr[i][j] = new Block(
                        new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE - Config.BLOCK_WIDTH
                                * (j), 10 * Config.BLOCK_HEIGHT - Config.BLOCK_HEIGHT
                                * (i)), Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                blocksArr[i][j].setColor(rowColors[i]);
                blocksArr[i][j].addToGame(game);
            }
        }

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(150, Config.BALL_SPEED));
        ball.addToGame(game);

        Ball ball2 = new Ball(Config.BALL_2NDSTARTING_POSITION, Config.BALL_RADIUS, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(80, Config.BALL_SPEED));
        ball2.addToGame(game);

        Block wallLeft = new Block(
                new Point(0,  Config.WALL_SIZE), Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallLeft.addToGame(game);

        Block wallRight = new Block(
                new Point(Config.WINDOW_WIDTH - Config.WALL_SIZE,  Config.WALL_SIZE),
                Config.WALL_SIZE, Config.WINDOW_HEIGHT - Config.WALL_SIZE);
        wallRight.addToGame(game);

        Block wallTop = new Block(
                new Point(0,  0), Config.WINDOW_WIDTH , Config.WALL_SIZE);
        wallTop.addToGame(game);

        Block wallBottom = new Block(
                new Point(0,  Config.WINDOW_HEIGHT - Config.WALL_SIZE), Config.WINDOW_WIDTH, Config.WALL_SIZE);
        wallBottom.addToGame(game);

        Paddle paddle = new Paddle(gui);
        paddle.addToGame(game);
    }
}
