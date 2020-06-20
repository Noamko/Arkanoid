package vector;

import geometry.Point;

/**
 * @author Noam Koren
 * id 308192871
 * ass2
 */
public class Velocity {
    private final double x;
    private final double y;

    /**
     * vector.Velocity constructor.
     * @param dx (double)
     * @param dy (double)
     */
    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * 2nd vector.Velocity constrcutor.
     * @param angle (double)
     * @param speed (double)
     * @return vector.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = angle * (Math.PI / 180);
        double dx = Math.sin(radians) * speed;
        double dy = -Math.cos(radians) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * get speed.
     * @return double
     */
    public double getSpeed() {
        return Math.sqrt(x * x + y * y);
    }
    /**
     * get x velocity.
     * @return double
     */
    public double getX() {
        return this.x;
    }

    /**
     * get y velocity.
     * @return double
     */
    public double getY() {
        return this.y;
    }

    /**
     * get a point returing a new point acording to velocity.
     * @param p (Point)
     * @return Point
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + x, p.getY() + y);
        return newPoint;
    }

    /**
     * return the angle of the vector.Velocity vector.
     * @return double
     */
    public double getAngle() {
        double angle = Math.atan2(this.getX(), this.getY() * -1);
        angle = angle * (180 / Math.PI); //converts to degrees
        return angle;
    }
}