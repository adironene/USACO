import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class WhereAmI {
    private static File file;

    public WhereAmI(File f) {
        file = f;
    }

    public static void main(String[] arg) throws Exception {
        //Assuming the input file is in the current directory
        file = new File("whereami.in");
        List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            lines.add(s);
        }
        KFinder kFinder = new KFinder(lines.get(1));
        PrintWriter writer = new PrintWriter("whereami.out", "UTF-8");
        writer.println(kFinder.findK());
        writer.close();
    }
}

class KFinder {
    String sequence;

    KFinder(String s) {
        sequence = s;
    }

    int findK() {
        int k = 0;
        boolean found = false;
        for (int subNum = 1; subNum <= sequence.length(); subNum++) {
            Set<String> store = new HashSet<>();
            for (int i = 0; i <= sequence.length() - subNum; i++) {
                String temp = sequence.substring(i, i + subNum);
                if (i == sequence.length() - subNum) {
                    if (!store.contains(temp)) {
                        k = subNum;
                        found = true;
                    }

                }
                if (store.contains(temp))
                    break;
                else {
                    store.add(temp);
                }

            }
            if (found) {
                return k;
            }
        }
        return k;
    }
}
