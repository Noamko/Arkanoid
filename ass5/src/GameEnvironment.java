import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private double shortestpath = 1000;
    private List<Collidable> collidables = new ArrayList<Collidable>();
    private Line shortestLine = new Line(0, 0, 0, 0);

    /**
     * add the given collidable to the environment.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removes a collidable from the game.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
            collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory Line
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        shortestLine = trajectory;
        if (collidables.isEmpty()) {
            return null;
        }
        Point closestPoint = null;
        Collidable closestCollidable = null;
        for (Collidable c : collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (c.getCollisionRectangle().isInside(trajectory.start())) {
                return new CollisionInfo(c, collisionPoint, Config.COLLISION_ERROR);
            }
            if (collisionPoint != null) {
                Line temp = new Line(trajectory.start(), collisionPoint);
                if (temp.length() < shortestLine.length()) {
                    shortestLine = temp; //trajectory.start().distance(collisionPoint);
                    closestPoint = collisionPoint;
                    closestCollidable = c;
                }
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestCollidable, closestPoint, 0);
    }
}
