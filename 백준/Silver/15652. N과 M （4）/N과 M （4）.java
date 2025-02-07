import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int M;
    public static int[] tgt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tgt = new int[M];

        perm(1, 0);

        bw.close();
    }

    public static void perm(int startNum, int tgtCnt) throws IOException {
        if (tgtCnt == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tgt[i] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (tgtCnt > 0 && tgt[tgtCnt - 1] > i) continue;
            tgt[tgtCnt] = i;
            perm(startNum, tgtCnt + 1);
        }
    }
}