import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
public class race {
    public static void main(String[] args) throws IOException {
        //long now = System.currentTimeMillis();
        BufferedReader f = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int raceLength = Integer.parseInt(st.nextToken());
        int numX = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numX; i++) {
            String s = f.readLine();
            out.println(new Racer(raceLength, Integer.parseInt(s)).run());
        }
        out.close();
        //System.out.println("time elapsed: " + (System.currentTimeMillis() - now));
    }
}
class Racer {
    final int raceDistance;
    final int finishingSpeed;
    private int time;
    private int curDistance;
    private int curSpeed;
    Racer(int raceDistance, int finishingSpeed) {
        this.raceDistance = raceDistance;
        this.finishingSpeed = finishingSpeed;
    }
    int run() {
        time = 0;
        curDistance = 0;
        curSpeed = 0;
        while (canSpeedUp() && raceDistance - curDistance > 0) {
            time++;
            curSpeed++;
            curDistance = curDistance + curSpeed;
        }
        while (raceDistance - curDistance > 0) {
            if (canRunInCurrentSpeed()) {
                if (curSpeed == finishingSpeed) {
                    time = time + (raceDistance - curDistance) / curSpeed;
                    if ((raceDistance - curDistance) % curSpeed != 0) {
                        time = time + 1;
                    }
                    curDistance = raceDistance;
                } else {
                    time++;
                    curDistance = curDistance + curSpeed;
                }
            } else {
                curSpeed--;
            }
        }
        return time;
    }
    boolean canSpeedUp() {
        int nextSpeed = curSpeed + 1;
        if (nextSpeed <= finishingSpeed) {
            return true;
        }
        int minimalDecelerationDistance = getMinDecelerationDistance(nextSpeed, finishingSpeed);
        return raceDistance - curDistance - minimalDecelerationDistance - nextSpeed > 0;
    }
    int getMinDecelerationDistance(int startSpeed, int endSpeed) {
        if (startSpeed <= endSpeed) {
            return 0;
        }
        return DecelerationDistanceTable.getDistance(startSpeed - 1) - DecelerationDistanceTable.getDistance(endSpeed);
    }
    boolean canRunInCurrentSpeed() {
        if (curSpeed == finishingSpeed) {
            return true;
        }
        int minimalDecelerationDistance = getMinDecelerationDistance(curSpeed, finishingSpeed);
        return raceDistance - curDistance - minimalDecelerationDistance - curSpeed > 0;
    }
}
class DecelerationDistanceTable {
    private static Map<Integer, Integer> cache = new HashMap<>();
    private static int maxSpeedInCache = 1;
    static {
        cache.put(1, 0);
        maxSpeedInCache = 1;
    }
    static int getDistance(int speed) {
        if (speed > maxSpeedInCache) {
            for(int i = maxSpeedInCache + 1; i <= speed; i++) {
                cache.put(i, getDistance(i - 1) + i);
            }
            maxSpeedInCache = speed;
        }
        return cache.get(speed);
    }
}
