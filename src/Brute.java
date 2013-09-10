
import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        String fileName = args[0];
        In in = new In(fileName);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        double slope1 = p.slopeTo(q);
                        double slope2 = p.slopeTo(r);
                        double slope3 = p.slopeTo(s);
                        if (slope1 == slope2 && slope1 == slope3) {
                            writeSegment(p, q, r, s);
                        }
                    }
                }
            }
        }
        
        for (int k = 0; k < n; k++) {
            points[k].draw();
        }
    }

    private static void writeSegment(Point... points) {
        System.out.print(points[0]);
        for (int i = 1; i < points.length; i++) {
            System.out.format(" -> %s", points[i].toString());
        }
        System.out.println();
        
        points[0].drawTo(points[points.length - 1]);
    }
}
