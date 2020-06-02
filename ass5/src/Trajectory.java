import biuoop.DrawSurface;

/**
 * Trajectory Class.
 */
public class Trajectory implements Sprite {
    private Line trajectoryLine;
    private double mul = 5;

    /**
     * Trajectory Constructor.
     * @param obj (Point)
     * @param v (velocity)
     */
    public Trajectory(Point obj, Velocity v) {
        this.trajectoryLine = new Line(
                obj.getX(), obj.getY(), obj.getX() + v.getX() * mul, obj.getY() + v.getY() * mul);
    }

    /**
     * Return the Trajectory line object.
     * @return Line
     */
    public Line getTrajectoryLine() {
        return trajectoryLine;
    }

    /**
     * update the trajectory line.
     * @param p (Point)
     * @param v (Velocity)
     */
    public void update(Point p, Velocity v) {
        this.trajectoryLine = new Line(
                p.getX(), p.getY(), p.getX() + v.getX() * mul, p.getY() + v.getY() * mul);
    }

    /**
     * draw the sprite to the screen.
     * @param d (DrawSurface)
     */
    public void drawOn(DrawSurface d) {
        trajectoryLine.drawOn(d);
    }

    /**
     * occurs each frame.
     * notify the sprite that time has passed
     */
    public void timePassed() {

    }
}
