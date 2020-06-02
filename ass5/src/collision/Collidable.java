package collision;
import geometry.Rectangle;
import geometry.Point;
import vector.Velocity;
import objects.Ball;
/**
 * collision.Collidable interface.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * returns the collision rect.
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * occurs when the ball objects hit a collidable.
     * @param collisionPoint (Point)
     * @param currentVelocity (vector.Velocity)
     * @param hitter Ball
     * @return vector.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * occurs when the ball objects hit a collidable.
     * @param collisionPoint (Point)
     * @param currentVelocity (vector.Velocity)
     * @param collidable collision.Collidable
     * @param hitter Ball
     * @return vector.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Collidable collidable);
}