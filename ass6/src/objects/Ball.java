package objects;
/**
 * @author Noam Koren
 * 308192871
 * ass3
 */
import biuoop.DrawSurface;
import collision.CollisionInfo;
import configuration.Config;
import gamelogic.Game;
import gamelogic.GameEnvironment;
import geometry.Point;
import java.awt.Color;
import java.util.Random;
import ui.Sprite;
import vector.Trajectory;
import vector.Velocity;

/**
 * Ball class.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color = Color.WHITE;
    private java.awt.Color stroke = Color.black;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Trajectory trajectory;
    private CollisionInfo collisionInfo;

    //Test
    //private Point nextHit = new Point(0,0);

    /**
     * Ball constructor.
     * @param center (point)
     * @param r (int)
     * @param ge (gamelogic.GameEnvironment)
     */
    public Ball(Point center, int r, GameEnvironment ge) {
        this.center = center;
        this.radius = r;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = ge;
        this.trajectory = new Trajectory(center, this.velocity);
    }

    /**
     * 2nd Ball constructor.
     * @param center (Point)
     * @param r (int)
     * @param color (Color)
     * @param ge (gamelogic.GameEnvironment)
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment ge) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = ge;
    }

    /**
     * 3rd Ball constructor.
     * @param x (int)
     * @param y (int)
     * @param r (int)
     * @param ge (gamelogic.GameEnvironment)
     */
    public Ball(int x, int y, int r, GameEnvironment ge) {
        this.center = new Point(x, y);
        this.radius = r;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = ge;
    }

    /**
     * set the color field of the Ball object.
     * @param c (Color)
     */
    public void setColor(Color c) {
        this.color = c;
    }
    /**
     * set the Stroke color field of the ball object.
     * @param c (Color)
     */
    public void setStroke(Color c) {
          this.stroke = c;
    }

    /**
     * get the x center.
     * @return int
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get the y center.
     * @return int
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the radius.
     * @return int radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get the color field.
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface (DrawSurface)
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.stroke);
        surface.drawCircle((int) (this.center.getX()), (int) (this.center.getY()), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) (this.center.getX()), (int) (this.center.getY()), this.radius);
//        surface.setColor(Color.RED);
//        surface.fillCircle((int) nextHit.getX(), (int) nextHit.getY(), 4);

    }

    /**
     * set the velocity filed.
     * @param v (vector.Velocity)
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * get the velocity field.
     * @return (vector.Velocity)
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    // notify the sprite that time has passed

    /**
     * occurs each frame.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * add the ball object to the game env.
     * @param game (gamelogic.Game)
     */
    public void addToGame(Game game) {
        game.addSprite(this);
//        game.addSprite(this.trajectory);
    }

    /**
     * remove the current ball object from the game.
     * @param game gamelogic.Game
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        this.velocity = Velocity.fromAngleAndSpeed(0, 0);
    }

    /**
     * Move the ball one step according to its velocity.
     */
    public void moveOneStep() {
        Random random = new Random();
        trajectory.update(this.center, this.velocity);
        collisionInfo = gameEnvironment.getClosestCollision(trajectory.getTrajectoryLine());
        if (collisionInfo != null) {
//            nextHit = collisionInfo.collisionPoint();
            Point nextPos = this.velocity.applyToPoint(this.center);
            //Collision detected
            if (collisionInfo.error() == Config.COLLISION_ERROR) {
//                System.out.println("COLLISION_ERROR");

                collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);

                velocity = Velocity.fromAngleAndSpeed(
                        this.velocity.getAngle() + 180 + random.nextInt(45), velocity.getSpeed());
                this.center = velocity.applyToPoint(this.center);
            } else if (nextPos.distance(collisionInfo.collisionPoint()) <= this.radius + this.velocity.getSpeed()) {
                if (collisionInfo.collisionObject().getClass() == Paddle.class) {
                    this.setVelocity(collisionInfo.collisionObject().hit(this,
                            collisionInfo.collisionPoint(), this.velocity));
                } else {
                    this.setVelocity(collisionInfo.collisionObject().hit(this,
                            collisionInfo.collisionPoint(), this.velocity, collisionInfo.collisionObject()));
                }
            }
        }
        this.center = this.velocity.applyToPoint(this.center);
    }
}