import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class randGen {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
        out.println(100000);
        for(int i=0;i<100000;i++){
            long number = new Random().nextInt(20000) -10000;
            long number2 = new Random().nextInt(20000) -10000;
            out.println(number + " "+number2);
        }
    }
}
