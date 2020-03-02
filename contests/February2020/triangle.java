import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.io.PrintWriter;
public class triangle {
    private static List[] yLib = new List[20001], xLib = new List[20001];
    private static Map<Point, Point> points = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        int numP = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numP; i++) {
            String[] inputs = reader.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]), y = Integer.parseInt(inputs[1]);
            int indexXLib = index(x),indexYLib = index(y);
            if (yLib[indexYLib] == null)
                yLib[indexYLib] = new ArrayList();
            if (xLib[indexXLib] == null)
                xLib[indexXLib] = new ArrayList();
            yLib[indexYLib].add(x);xLib[indexXLib].add(y);
            Point p = new Point(x, y);
            points.put(p, p);
        }
        reader.close();
        pointsInitializer();
        long areaTot = 0;
        for (Point point: points.values()) {
            areaTot = areaTot + point.v * point.w;
        }
        writer.println((areaTot % 1000000007));
        writer.close();
    }
    static void pointsInitializer() {
        for(int i = 0; i < xLib.length; i++) {
            int x = i - 10000;
            List<Integer> allYVals = xLib[i];
            if (allYVals != null) {
                Collections.sort(allYVals);
                List<Integer> oneVal = sumFinder(allYVals);
                for (int j = 0; j < allYVals.size(); j++)
                    setterGetter(x, allYVals.get(j)).v = oneVal.get(j);

            }
        }
        for (int i = 0; i < yLib.length; i++) {
            int y = i - 10000;
            List<Integer> allXVals = yLib[i];
            if (allXVals != null) {
                Collections.sort(allXVals);
                List<Integer> twoVal = sumFinder(allXVals);
                for (int j = 0; j < allXVals.size(); j++)
                    setterGetter(allXVals.get(j), y).w = twoVal.get(j);
            }
        }
    }
    static List<Integer> sumFinder(List<Integer> points) {
        Integer sum0 = 0;
        for(Integer point: points)
            sum0 = sum0 + Math.abs(points.get(0) - point);
        List<Integer> answer = new ArrayList<>();
        answer.add(sum0);
        int size = points.size();
        for (int i = 1; i < points.size(); i++) {
            int prev = answer.get(i - 1), diff = Math.abs(points.get(i) - points.get(i - 1));
            int sum = prev - (size - 2 * i) * diff;
            answer.add(sum);
        }
        return answer;
    }
    static Point setterGetter(int x, int y) {
        return points.get(new Point(x, y));
    }


    static int index(int x) {
        return 10000 + x;
    }

    static class Point {
        final int x,y;
        long v,w;
        public Point(int xVal, int yVal) {
            x=xVal;y=yVal;
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
