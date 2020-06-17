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

    public DirectHit() {
        blocks = new ArrayList<>();
        bg = new DHBackground();
    }

    public void load(GameLevel gl) {
//        gl.addSprite(getBackground());
        theRedBlockOfTerror = new Block(new Point(Config.WINDOW_WIDTH/2 -15,175 - 15),30,30);
        theRedBlockOfTerror.setColor(Color.RED);
        blocks.add(theRedBlockOfTerror);

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        ball.setVelocity(Velocity.fromAngleAndSpeed(0, Config.BALL_SPEED));

        ball.addToGame(gl);
        Paddle paddle = new Paddle();
        paddle.addToGame(gl);
    }

    @Override
    public Paddle getPaddle() {
        return null;
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
        Stripe s1 = new Stripe(new Line(Config.WINDOW_WIDTH/2,50,Config.WINDOW_WIDTH/2,300), Color.BLUE);
        Stripe s2 = new Stripe(new Line(250,175,550,175), Color.BLUE);
        Block back = new Block(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        Circle outerCircle = new Circle(s1.getLine().middle(), 100);
        outerCircle.setStroke(Color.BLUE);
        Circle innerCircle = new Circle(s1.getLine().middle(), 50);
        innerCircle.setStroke(Color.BLUE);
        back.setColor(Color.BLACK);

        super.getSprites().add(back);
        super.getSprites().add(s1);
        super.getSprites().add(s2);
        super.getSprites().add(outerCircle);
        super.getSprites().add(innerCircle);
    }
}
