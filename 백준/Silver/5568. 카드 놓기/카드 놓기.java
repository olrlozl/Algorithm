import java.io.*;
import java.util.*;

public class Main {
    public static int n, k;
    public static int[] src, tgt;
    public static boolean[] visit;
    public static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        src = new int[n];
        tgt = new int[k];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            src[i] = Integer.parseInt(br.readLine());
        }

        set = new HashSet<>();
        perm(0);

        bw.write(set.size() + "");
        bw.close();
    }

    public static void perm(int tgtIdx) {
        if (tgtIdx == k) {
            StringBuilder sb = new StringBuilder();
            for (int t : tgt) sb.append(t);
            set.add(Integer.parseInt(sb.toString()));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                tgt[tgtIdx] = src[i];

                visit[i] = true;
                perm(tgtIdx + 1);
                visit[i] = false;
            }
        }
    }
}