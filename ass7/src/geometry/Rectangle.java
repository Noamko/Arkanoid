package geometry;
import java.util.ArrayList;

/**
 * Rectangle Class.
 */
public class Rectangle {
    private Point upperleft;
    private double width, height;


    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft Point
     * @param width double
     * @param height double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperleft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points.
     * with the specified line.
     * @param line Line
     * @return List
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interPoints = new ArrayList<Point>();
        Line[] lines = getRectLines();
        for (int i = 0; i < lines.length; i++) {
            Point interPoint = line.intersectionWith(lines[i]);
            if (interPoint != null) {
                interPoints.add(interPoint);
            }
        }
        return interPoints;
    }

    /**
     * Return the width and height of the rectangle.
     * @return double
     */
    public double getWidth() {
        return this.width;

    }

    /**
     * get the height of the rect.
     * @return double
     */
    public double getHeight() {
        return  this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Point
     */
    public Point getUpperLeft() {
        return this.upperleft;
    }

    /**
     * Change the position of the rect.
     * @param p Point
     */
    public void setUpperleft(Point p) {
        this.upperleft = p;
    }

    /**
     * return the rect stroke line array.
     * @return Line[]
     */
    public Line[] getRectLines() {
        Point lowerleft = new Point(upperleft.getX(), upperleft.getY() + this.height);
        Point upperright = new Point(upperleft.getX() + this.width, upperleft.getY());
        Point lowerright = new Point(upperleft.getX() + this.width, upperleft.getY() + this.height);

        Line left = new Line(upperleft, lowerleft);
        Line top = new Line(upperleft, upperright);
        Line right = new Line(lowerright, upperright);
        Line bottom = new Line(lowerright, lowerleft);
        Line[] lines = {top, right, bottom, left};
        return lines;
    }

    /**
     * Checks if a point is inside a rect.
     * @param p Point
     * @return bool
     */
    public boolean isInside(Point p) {
        return (p.getX() > this.getUpperLeft().getX()
                && p.getX() < this.getUpperLeft().getX() + this.getWidth()
                && p.getY() > this.getUpperLeft().getY()
                && p.getY() < this.getUpperLeft().getY() + this.getHeight());
    }
}
