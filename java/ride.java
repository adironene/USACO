/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: yeahyeet
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.*;

class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
        String line1 = f.readLine();
        String line2 = f.readLine();
        StringTokenizer st1 = new StringTokenizer(line1);
        StringTokenizer st2 = new StringTokenizer(line2);
        String s1 = st1.nextToken();
        String s2 = st2.nextToken();
        int n1 = getVal(s1);
        int n2 = getVal(s2);
        if((n1 % 47) == (n2 % 47))
            out.println("GO");
        else
            out.println("STAY");
        out.close();                               
    }

    static int getVal(String s) {
        char[] c = s.toCharArray();
        int sum = 1;
        for (char ch : c) {
            int pos = (int)ch -(int)('A')+1;
            sum *= pos;
        }
        return sum;
    }
}
