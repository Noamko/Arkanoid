/**
 * CollisionInfo Class.
 */
public class CollisionInfo {
    private Collidable collidableObject;
    private Point collisionPoint;
    private int error;


    /**
     * the point at which the collision occurs.
     * @param c (Collidable)
     * @param collisionPoint (Point)
     * @param e (int)
     */
    public  CollisionInfo(Collidable c, Point collisionPoint, int e) {
        this.collidableObject = c;
        this.collisionPoint = collisionPoint;
        this.error = e;
    }

    /**
     * return the Collision Point.
     * @return Point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return Collidable
     */
    public Collidable collisionObject() {
        return collidableObject;
    }

    /**
     * return the collision error code.
     * @return int
     */
    public int error() {
        return  this.error;
    }
}