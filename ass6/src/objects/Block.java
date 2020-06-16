package objects;
import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import gamelogic.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import ui.Sprite;
import vector.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Block class.
 * implement a collision.Collidable and a sprite
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Color color;
    private Rectangle rect;

    /**
     * Block constructor.
     * @param position (Point)
     * @param width (double)
     * @param height (double)
     */
    public Block(Point position, double width, double height) {
        this.color = Color.gray;
        rect = new Rectangle(position, width, height);
        hitListeners = new ArrayList<>();
    }

    /**
     * returns the rectangle object of the block.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * this function is called from the ball class when a collision to the block has been detected.
     * from the ball trajectory we calculate a new velocity to the ball depends on it hitting angle
     * and we return the new velocity
     * @param collisionPoint  (Point)
     * @param currentVelocity (vector.Velocity)
     * @param collidable (collision.Collidable)
     * @param hitter Ball
     * @return vector.Velocity (vector.Velocity)
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Collidable collidable) {
        Random random = new Random();
        double angleBias = random.nextInt(20);
        rect = collidable.getCollisionRectangle();
        Line[] lines = rect.getRectLines();
        double currentAngle = currentVelocity.getAngle();
        double realAngle = currentAngle * -1 + 90;
        int pointCollisionFlag = 0;
        Velocity velocity = Velocity.fromAngleAndSpeed(0, 0);
        //top
        if (lines[0].isPointInLine(collisionPoint) || lines[2].isPointInLine(collisionPoint)) {
            if (currentAngle > 0) {
                velocity = Velocity.fromAngleAndSpeed((-realAngle) * -1 + 90, currentVelocity.getSpeed());
            } else {
                velocity = Velocity.fromAngleAndSpeed((360 - realAngle) * -1 + 90, currentVelocity.getSpeed());
            }
            pointCollisionFlag += 1;
        } else if (lines[1].isPointInLine(collisionPoint) || lines[3].isPointInLine(collisionPoint)) {
            velocity = Velocity.fromAngleAndSpeed((180 - realAngle) * -1 + 90, currentVelocity.getSpeed());
            pointCollisionFlag += 1;
        }

        this.notifyHit(hitter);

        if (pointCollisionFlag > 1) {
            return Velocity.fromAngleAndSpeed(currentAngle + 180, currentVelocity.getSpeed());
        }
        return Velocity.fromAngleAndSpeed(velocity.getAngle() + angleBias, velocity.getSpeed());
    }

    /**
     * a Second unused Hit constructor.
     * @param collisionPoint (Point)
     * @param currentVelocity (vector.Velocity)
     * @param hitter Ball
     * @return vector.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        return null;
    }

    /**
     * add the block object to the game environment.
     * @param gameLevel (gamelogic.Game)
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removes the current block object from the game.
     * @param gameLevel gamelogic.Game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Draw the Block on a given drawSurface object.
     * @param d (DrawSurface)
     */
    public void drawOn(DrawSurface d) {
        Point upperleft = this.rect.getUpperLeft();
        d.setColor(this.color);
        d.fillRectangle((int) upperleft.getX(), (int) upperleft.getY(), (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) upperleft.getX(), (int) upperleft.getY(), (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    /**
     * set the Color of the block object.
     * @param c (Color)
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Occurs each frame.
     */
    public void timePassed() {

    }

    /**
     * occurs each time the block is being hit by the ball.
     * @param hitter Ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * add a hit listener to the block obj.
     * @param hl collision.HitListener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes a hit listener to the block obj.
     * @param hl collision.HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
