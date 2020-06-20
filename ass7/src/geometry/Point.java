package geometry;
/**
 * @author Noam Koren
 * 308192871
 * ass5
 */
public class Point {
    private  double x, y;

    /**
     * Point constructor.
     * @param x (double)
     * @param y (double)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns distance between point object and another point.
     * @param p (point)
     * @return (double)
     */
    public double distance(Point p) {
        double dis = Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2));
        return dis;
    }

    /**
     * checks if 2 points are equal.
     * @param p (Point)
     * @return (boolean)
     */
    public boolean equals(Point p) {
        return (p.getY() == this.getY() && p.getX() == this.getX());
    }

    /**
     * get x.
     * @return (double)
     */
    public double getX() {
        return  this.x;
    }
    /**
     * get y.
     * @return (double)
     */
    public double getY() {
        return this.y;
    }
}
