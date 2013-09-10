/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private int x;
    
    private int y;
    
    private class PointComparator implements Comparator<Point> {

        private Point p;
        
        public PointComparator(Point p) {
            this.p = p;
        }
        
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = p.slopeTo(o1);
            double slope2 = p.slopeTo(o2);
            if (slope1 < slope2) {
                return -1;
            }
            if (slope1 == slope2) {
                return 0;
            }
            return 1;
        }
        
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }


    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }


    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y == that.y) {
            if (this.x < that.x) {
                return -1;
            } else if (this.x == that.x) {
                return 0;
            }
        }
        return 1;
    }


    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }
        if (this.y == that.y) {
            return +0.0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }
    
    // compare points by slope to this point
    public final Comparator<Point> SLOPE_ORDER = new PointComparator(this);
}
