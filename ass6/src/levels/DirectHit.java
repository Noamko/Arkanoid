package levels;

import biuoop.KeyboardSensor;
import configuration.Config;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import geometry.Line;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import ui.Background;
import ui.Circle;
import ui.Sprite;
import ui.Stripe;
import vector.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    private Block theRedBlockOfTerror;
    private List<Block> blocks;
    private DHBackground bg;
    Paddle paddle;

    public DirectHit() {
        blocks = new ArrayList<>();
        bg = new DHBackground();
        paddle = new Paddle();
    }

    //TODO: should match new initilize method with padded added ouside this class
    public void load(GameLevel gl) {
        theRedBlockOfTerror = new Block(new Point(Config.WINDOW_WIDTH/2 -15,175 - 15),30,30);
        theRedBlockOfTerror.setColor(Color.RED);
        blocks.add(theRedBlockOfTerror);

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        ball.setVelocity(Velocity.fromAngleAndSpeed(180, Config.BALL_SPEED));
        ball.addToGame(gl);
    }

    @Override
    public Paddle getPaddle() {
        return paddle;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return Config.PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return Config.PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return bg;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}

class DHBackground extends Background {
    public DHBackground (){
        Line l = new Line(Config.WINDOW_WIDTH/2,50,Config.WINDOW_WIDTH/2,300);
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.black);
        addLine(l, Color.BLUE);
        addLine(new Line(250,175,550,175), Color.BLUE);
        addCircle(l.middle(), 100, Color.BLUE, true);
        addCircle(l.middle(), 50, Color.BLUE, true);
    }
}
