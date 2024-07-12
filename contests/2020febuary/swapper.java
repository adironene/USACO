import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class swapper {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pairs = new Pair[2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            pairs[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();
        initIndices();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        int[] result = new int[n];
        for(int i = 0; i < result.length; i++) {
            int index = findIndex(i, k, new ArrayList<>());
            result[index] = i;
        }
        for(int index: result) {
            writer.println((index + 1));
        }
        writer.close();
    }
    static Pair[] pairs;
    static int n;
    static int k;
    static int[] indices;
    static void initIndices() {
        int[] result = new int[n];
        for(int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        for (Pair pair: pairs) {
            for(int k = 0; k < (pair.r - pair.l + 1) / 2; k++) {
                int left = pair.l - 1 + k,right = pair.r - 1 - k,temp = result[left];
                result[left] = result[right];
                result[right] = temp;
            }
        }
        indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[result[i]] = i;
        }
    }
    static int findIndex(int curIndex, int steps, List<Integer> path) {
        if (!path.isEmpty()) {
            int remainder = steps % path.size();
            return remainder == 0 ? curIndex : path.get(remainder - 1);
        }
        int nexIndex = indices[curIndex];
        path.add(nexIndex);
        steps--;
        while(steps > 0 && path.get(path.size() - 1) != curIndex) {
            nexIndex = indices[nexIndex];
            path.add(nexIndex);
            steps--;
        }
        return steps == 0 ? nexIndex : findIndex(curIndex, steps, path);
    }
    static class Pair {
        int l, r;
        Pair(int lval, int rval) {
            l = lval;
            r = rval;
        }
    }
}
