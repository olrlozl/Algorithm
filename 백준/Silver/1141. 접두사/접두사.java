import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) arr[i] = br.readLine();

        Arrays.sort(arr, (a, b) -> b.length() - a.length());

        HashSet<String> set = new HashSet<>();

        for (String a: arr) {
            if (set.isEmpty()) {
                set.add(a);
                continue;
            }

            boolean flag = true;
            for (String s : set) {
                if (s.indexOf(a) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                set.add(a);
            }
        }

        bw.write(set.size() + "");
        bw.close();
    }
}