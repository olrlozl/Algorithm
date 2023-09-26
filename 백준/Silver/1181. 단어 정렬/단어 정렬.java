import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Set<String> word = new TreeSet<>();
        Set<Integer> len = new TreeSet<>();

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            word.add(s);
            len.add(s.length());
        }

        for (int i : len) {
            for (String w : word) {
                if (w.length() == i) {
                    bw.write(w + "\n");
                }
            }
        }
        bw.close();
    }
}