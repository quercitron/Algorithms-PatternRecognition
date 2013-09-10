
import java.util.Arrays;
import java.util.Comparator;

public class Fast {

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        String fileName = args[0];
        In in = new In(fileName);
        int n = in.readInt();
        Point[] mainPoints = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            mainPoints[i] = new Point(x, y);
        }
        // Arrays.sort(points);

        for (int k = 0; k < n; k++) {
            if (n < 4) {
                break;
            }
            Point[] points = Arrays.copyOf(mainPoints, mainPoints.length);
            Comparator<Point> comparator = mainPoints[k].SLOPE_ORDER;
            Arrays.sort(points, comparator);
            int i = 1;
            boolean incr = points[0].compareTo(points[1]) <= 0;
            for (int j = 2; j < n; j++) {
                if (points[0].slopeTo(points[i]) == points[0].slopeTo(points[j])) {
                    if (points[0].compareTo(points[j]) > 0) {
                        incr = false;
                    }
                } else {
                    if (incr && j - i >= 3) {
                        writeSegment(points, i, j);
                    }
                    incr = points[0].compareTo(points[j]) <= 0;
                    i = j;
                }
            }
            if (incr && n - i >= 3) {
                writeSegment(points, i, n);
            }
        }
        
        for (int k = 0; k < n; k++) {
            mainPoints[k].draw();
        }
    }

    private static void writeSegment(Point[] points, int left, int right) {
        Point[] subArray = Arrays.copyOfRange(points, left, right);
        Arrays.sort(subArray);
        System.out.print(points[0]);
        for (int i = 0; i < subArray.length; i++) {
            System.out.format(" -> %s", subArray[i].toString());
        }
        System.out.println();
        
        points[0].drawTo(subArray[subArray.length - 1]);
    }
}
