import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * Paddle class.
 */
public class Paddle implements Collidable, Sprite {
    private static final Point STARTING_POSITION = new Point(
            Config.WINDOW_WIDTH / 2 - Config.PADDLE_WIDTH / 2,
            Config.WINDOW_HEIGHT - Config.PADDLE_HEIGHT * 2 - 10);
    private Block blockPaddle;
    private Velocity velocity;
    private biuoop.KeyboardSensor keyboard;

    /**
     * Paddle constructor.
     * @param gui Gui
     */
    public Paddle(GUI gui) {
        blockPaddle = new Block(STARTING_POSITION, Config.PADDLE_WIDTH, Config.PADDLE_HEIGHT);
        blockPaddle.setColor(Color.orange);
        this.velocity = new Velocity(Config.PADDLE_SPEED, 0);
        keyboard = gui.getKeyboardSensor();
    }
    /**
     * Move the paddle to the left according its velocity.
     */
    public void moveLeft() {
        this.velocity = new Velocity(Config.PADDLE_SPEED * -1, 0);
        blockPaddle.getCollisionRectangle().setUpperleft(
                velocity.applyToPoint(this.blockPaddle.getCollisionRectangle().getUpperLeft()));
    }

    /**
     * Move the paddle to the right according its velocity.
     */
    public void moveRight() {
        this.velocity = new Velocity(Config.PADDLE_SPEED, 0);
        blockPaddle.getCollisionRectangle().setUpperleft(
                velocity.applyToPoint(this.blockPaddle.getCollisionRectangle().getUpperLeft()));
    }

    /**
     * occurs each frame.
     */
    public void timePassed() {
        if (keyboard.isPressed("right")) {
            if (this.blockPaddle.getCollisionRectangle().getUpperLeft().getX()
                    + Config.PADDLE_WIDTH < Config.WINDOW_WIDTH - Config.WALL_SIZE) {
                moveRight();
            }
        }

        if (keyboard.isPressed("left")) {
            if (this.blockPaddle.getCollisionRectangle().getUpperLeft().getX()  >  Config.WALL_SIZE) {
                moveLeft();
            }
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
     * Collidable.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockPaddle.getCollisionRectangle();
    }

    /**
     * occurs when the ball hit the paddle.
     * @param collisionPoint (Point)
     * @param currentVelocity (Velocity)
     * @param collidable (Collidable)
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Collidable collidable) {
        return currentVelocity;
    }

    /**
     * occurs when the ball hit the paddle.
     * @param collisionPoint (Point)
     * @param currentVelocity (Velocity)
     * @return Velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int[] angles = {300, 330, 0, 30, 60};
        for (int i  = 1; i <= 5; i++) {
            if (collisionPoint.distance(
                    this.getCollisionRectangle().getUpperLeft()) <= (Config.PADDLE_WIDTH / 5) * i
                    && collisionPoint.distance(
                            this.getCollisionRectangle().getUpperLeft()) > (Config.PADDLE_WIDTH / 5) * (i - 1)) {
                return Velocity.fromAngleAndSpeed(
                        angles[i - 1], currentVelocity.getSpeed());
            }
        }
        return Velocity.fromAngleAndSpeed(currentVelocity.getAngle() + 180, currentVelocity.getSpeed());
    }

    // Add this paddle to the game.
    /**
     * add the Paddle to the game.
     * @param g Game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
