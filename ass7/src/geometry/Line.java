package geometry;
import biuoop.DrawSurface;
import configuration.Config;

/**
 * @author Noam Koren
 * 308192871
 * ass5
 */
public class Line {
    private Point start, end;
    private double slope;
    private double yInter;
    private boolean isVertical = true;

    /**
     * 1st constructor.
     * @param start (Point)
     * @param end (Point)
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() != end.getX()) {
            this.slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
            this.isVertical = false;
            this.yInter = this.slope * start.getX() * -1 + start.getY();
        }
    }

    /**
     *  2nd Constructor.
     * @param x1 (double)
     * @param y1 (double)
     * @param x2 (double)
     * @param y2 (double)
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (x1 != x2) {
            this.slope = (y1 - y2) /  (x1 - x2);
            this.yInter = this.slope * x1 * -1 + y1;
            this.isVertical = false;
        }
    }

    /**
     * get the length of the line object.
     * @return (double)
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * get the middle point of the line object.
     * @return (Point)
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * get the start point of the line object.
     * @return (Point)
     */
    public Point start() {
        return this.start;
    }

    /**
     * get the end point of the line object.
     * @return (Point)
     */
    public Point end() {
        return this.end;
    }

    /**
     * checks if the line object intersenct with another line.
     * @param line (Line)
     * @return (boolen)
     */
    public boolean isIntersecting(Line line) {
        return intersectionWith(line) != null;
    }
    /**
     * returns the point witch the line object intersect with another line.
     * @param line (Line)
     * @return (Point)
     */

    public Point intersectionWith(Line line) {
            //Vertical lines Logic
            if (this.isVertical) {
                double xIntersection = this.start().getX();
                Point intersectionPoint = new Point(xIntersection, line.valueAt(xIntersection));
                if (this.isPointInLine(intersectionPoint) && line.isPointInLine(intersectionPoint)) {
                    return intersectionPoint;
                }
                return null;
            } else if (line.isVertical) {
                double xIntersection = line.start.getX();
                Point intersectionPoint = new Point(xIntersection, this.valueAt(xIntersection));
                if (this.isPointInLine(intersectionPoint) && line.isPointInLine(intersectionPoint)) {
                    return intersectionPoint;
                }
                return null;
            } //END of vertical lines logic
            double xIntersection = (line.getYInter() - this.yInter) / (this.slope - line.getSlope());
            double yIntersection = this.valueAt(xIntersection);
            Point intersectionPoint = new Point(xIntersection, yIntersection);
            if (this.isPointInLine(intersectionPoint) && line.isPointInLine(intersectionPoint)) {
                return intersectionPoint;
            }
            return  null;
    }
    /**
     * checks if 2 lines have the same starting and ending points.
     * @param line (Line)
     * @return (boolean)
     */
    public boolean equals(Line line) {
        return (this.start == line.start() && this.end == line.end());
    }

    /**
     * get the slope of the line if exists.
     * @return (double)
     */
    public double getSlope() {
        if (this.isVertical) {
           try {
                throw new Exception("Vertical lines have no SLOPE");
           } catch (Exception e) {
                return Config.INVALID_SLOPE;
           }
        }
        return this.slope;
    }

    /**
     * get the y intersection value.
     * @return (dobule)
     */
    public double getYInter() {
        return this.yInter;
    }

    /**
     * return the value at a certain x value and -1(error) if point is not inside the line.
     * @param x (double)
     * @return double
     */
    public double valueAt(double x) {
        if (this.isVertical) {
            return Config.INVALID_SLOPE;
        }
        double fvalue = this.slope * x + this.yInter;
        Point p = new Point(x, fvalue);
        if (!isPointInLine(p)) {
            return Config.INVALID_SLOPE;
        }
        return fvalue;
    }

    /**
     * checks if a point is inside a line object.
     * @param p (Point)
     * @return (boolean)
     */
    public boolean isPointInLine(Point p) {
        if (this.isVertical) {
            return  (this.start.getX() == p.getX() && (p.distance(this.middle()) <= this.length() / 2));
        }
        return ((p.distance(this.middle()) <= this.length() / 2) && p.getY() == (this.slope * p.getX() + this.yInter));
    }

    /**
     * return the closest interseaction Point to the start of the line.
     * @param rect rectangle.
     * @return Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        double minDistance = this.start.distance(rect.intersectionPoints(this).get(0));
        Point closestPoint = rect.intersectionPoints(this).get(0);
        Line[] lines = rect.getRectLines();

        for (Point p : rect.intersectionPoints(this)) {
            double distance = this.start.distance(p);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = p;
            }
        }
        return closestPoint;
    }

    /**
     * draw the line on a given DrawSurface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
//        d.setColor(Color.black);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * return true if line is vertical.
     * @return bool
     */
    public boolean isVertical() {
        return  isVertical;
    }

}