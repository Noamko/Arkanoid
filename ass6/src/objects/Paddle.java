package objects;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import configuration.Config;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import ui.Sprite;
import vector.Velocity;

import java.awt.Color;

/**
 * Paddle class.
 */
public class Paddle implements Collidable, Sprite {
    private static final Point STARTING_POSITION = new Point(
            Config.WINDOW_WIDTH / 2 - Config.PADDLE_WIDTH / 2,
            Config.WINDOW_HEIGHT - Config.PADDLE_HEIGHT);
    private Block blockPaddle;
    private Velocity velocity;
    private int width;
    private int diraction;

    /**
     * Paddle constructor.
     */
    public Paddle() {
        width = Config.PADDLE_WIDTH;
        blockPaddle = new Block(STARTING_POSITION, Config.PADDLE_WIDTH, Config.PADDLE_HEIGHT);
        blockPaddle.setColor(Color.orange);
        this.velocity = new Velocity(Config.PADDLE_SPEED, 0);
//        keyboard = k;
        diraction = 1;
    }
    /**
     * Move the paddle to the left according its velocity.
     */
    public void moveLeft() {
        diraction = -1;
        this.velocity = new Velocity(Config.PADDLE_SPEED * -1, 0);
    }

    /**
     * Move the paddle to the right according its velocity.
     */
    public void moveRight() {
        diraction = 1;
        this.velocity = new Velocity(Config.PADDLE_SPEED, 0);
    }

    public void stopMoving() {
        this.velocity = new Velocity(0, 0);
    }

    /**
     * occurs each frame.
     */
    public void timePassed() {
        if(diraction == 1 && this.blockPaddle.getCollisionRectangle().getUpperLeft().getX()
                    + this.width < Config.WINDOW_WIDTH - Config.WALL_SIZE) {
            blockPaddle.getCollisionRectangle().setUpperleft(
                    velocity.applyToPoint(this.blockPaddle.getCollisionRectangle().getUpperLeft()));
        }
        else if (diraction == -1 && this.blockPaddle.getCollisionRectangle().getUpperLeft().getX()  >  Config.WALL_SIZE) {
            blockPaddle.getCollisionRectangle().setUpperleft(
                    velocity.applyToPoint(this.blockPaddle.getCollisionRectangle().getUpperLeft()));
        }
    }

    /**
     * Draw the Paddle on a DrawSurface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        blockPaddle.drawOn(d);
    }

    /**
     * collision.Collidable.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockPaddle.getCollisionRectangle();
    }

    /**
     * occurs when the ball hit the paddle.
     * @param collisionPoint (Point)
     * @param currentVelocity (vector.Velocity)
     * @param collidable (collision.Collidable)
     * @param hitter Ball
     * @return vector.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Collidable collidable) {
        return currentVelocity;
    }

    /**
     * occurs when the ball hit the paddle.
     * @param collisionPoint (Point)
     * @param currentVelocity (vector.Velocity)
     * @param hitter Ball
     * @return vector.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int[] angles = {300, 330, 0, 30, 60};
        for (int i  = 1; i <= 5; i++) {
            if (collisionPoint.distance(
                    this.getCollisionRectangle().getUpperLeft()) <= (this.width / 5) * i
                    && collisionPoint.distance(
                            this.getCollisionRectangle().getUpperLeft()) > (this.width / 5) * (i - 1)) {
                return Velocity.fromAngleAndSpeed(
                        angles[i - 1], currentVelocity.getSpeed());
            }
        }
        return Velocity.fromAngleAndSpeed(currentVelocity.getAngle() + 180, currentVelocity.getSpeed());
    }

    // Add this paddle to the game.
    /**
     * add the Paddle to the game.
     * @param g gamelogic.Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    public void setVelocity(int v) {
        this.velocity = new Velocity(v,0);
    }

    public void setWidth(double w) {
        blockPaddle = new Block(STARTING_POSITION, w, Config.PADDLE_HEIGHT);
        blockPaddle.setColor(Color.orange);
        this.width = (int) w;
    }

    public int getWidth() {
        return this.width;
    }
}
