import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int M;
    public static int[] tgt;
    public static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tgt = new int[M];
        visit = new boolean[N + 1];

        comb(1, 0);
        bw.close();
    }

    public static void comb(int startNum, int tgtCnt) throws IOException {
        if (tgtCnt == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tgt[i] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = startNum; i <= N; i++) {
            if (!visit[i]) {
                tgt[tgtCnt] = i;
                visit[i] = true;
                comb(i + 1, tgtCnt + 1);
                visit[i] = false;
            }
        }
    }
}