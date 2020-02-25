import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gymnastics {
    private File file;
    List<String[]> lines = new ArrayList<>();
    List<Cow> cows = new ArrayList<>();
    static int numCows;
    int numTrials;

    private Gymnastics(File f) {
        file = f;
    }

    public static void main(String[] arg) throws Exception {
        //Assuming the input file is in the current directory
        File f = new File("gymnastic.in");
        Gymnastics c = new Gymnastics(f);
        PrintWriter writer = new PrintWriter("gymnastics.out", "UTF-8");
        if (c.store()) {
            c.assignCows();
            if (numCows == 1)
                writer.println(0);
            else if (numCows == 1)
                writer.println(1);
            writer.println(c.compareCows());
        } else {
            writer.println("wrong input, please try again");
        }
        writer.close();
    }

    private boolean store() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] input = s.split(" ");
            lines.add(input);
        }
        try {
            numTrials = Integer.parseInt(lines.get(0)[0]);
            numCows = Integer.parseInt(lines.get(0)[1]);
            lines.remove(0);
        } catch (Exception c) {
            return false;
        }
        return checkError();
    }

    private boolean checkError() {
        if (this.numTrials != lines.size())
            return false;
        for (String[] line : lines) {
            if (line.length != this.numCows)
                return false;
            for (String s : line) {
                try {
                    Integer.parseInt(s);
                } catch (Exception c) {
                    return false;
                }
            }
        }

        return true;
    }

    private int indexFinder(String[] arr, int t) {
        int i = 0;
        int len = arr.length;
        while (i < len) {
            int k = Integer.parseInt(arr[i]);
            if (k == t) {
                return i + 1;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    private void assignCows() {
        for (int c = 1; c < numCows + 1; c++) {
            List<Integer> compResultTemp = new ArrayList<>();
            for (int a = 0; a < numTrials; a++) {
                compResultTemp.add(indexFinder(this.lines.get(a), c));
            }
            cows.add(new Cow(c, compResultTemp));
        }
        cows.stream().map(Cow::compResults).forEach(System.out::println);
    }

    private int compareCows() {
        int count = 0;
        for (int c1 = 0; c1 < this.cows.size() - 1; c1++) {
            for (int c2 = c1 + 1; c2 < this.cows.size(); c2++) {
                int verify = this.cows.get(c1).compareTo(this.cows.get(c2));
                if (verify == 1)
                    count++;
            }
        }
        return count;
    }

    static class Cow implements Comparable<Cow> {
        List<Integer> results = new ArrayList<>();

        Cow(int i, List<Integer> comp) {
            results = comp;
        }

        int numComp() {
            return this.results.size();
        }

        List<Integer> compResults() {
            return results;
        }

        @Override
        public int compareTo(Cow c) {
            int numVerified = 0;
            if (this.results.get(0) > c.results.get(0)) {
                for (int i = 0; i < numComp(); i++) {
                    if (this.results.get(i) > c.results.get(i))
                        numVerified++;
                }
            } else if (this.results.get(0) < c.results.get(0)) {
                for (int i = 0; i < numComp(); i++) {
                    if (this.results.get(i) < c.results.get(i))
                        numVerified++;
                }
            }
            //0 is not
            //1 is consistent
            //-1 is anything that goes wrong
            if (numVerified == numComp())
                return 1;
            else if (numVerified != numComp())
                return 0;
            else
                return -1;
        }
    }
}
