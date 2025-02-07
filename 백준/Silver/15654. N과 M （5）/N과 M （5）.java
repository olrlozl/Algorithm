import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int M;
    public static boolean[] visit;
    public static int[] src;
    public static int[] tgt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        src = new int[N];
        tgt = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(src);
        perm(0);

        bw.close();
    }

    public static void perm(int tgtIdx) throws IOException {
        if (tgtIdx == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tgt[i] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                tgt[tgtIdx] = src[i];
                visit[i] = true;
                perm(tgtIdx + 1);
                visit[i] = false;
            }
        }
    }
}