import java.io.*;
import java.util.*;

public class Main {
    public static int[][] arr;
    public static int score;
    public static int factorial;
    public static int min = Integer.MAX_VALUE;


    public static int Permutation(int[] team, boolean[] visit, int[] tgt, int idx) {
        if (idx == 2) {
            score += arr[tgt[0]][tgt[1]];
            return score;
        }
        for (int i = 0; i < team.length; i++) {
            if (!visit[i]) {
                tgt[idx] = team[i];
                visit[i] = true;
                Permutation(team, visit, tgt, idx + 1);
                visit[i] = false;
            }
        }
        return score;
    }

    public static void Combination(boolean[] src, int idx, int cnt) {
        if (cnt == src.length / 2) {
            if (factorial-- == 0) return;
            int[] A = new int[src.length / 2];
            int[] B = new int[src.length / 2];
            for (int i = 0, a = 0, b = 0; i < src.length; i++) {
                if (src[i]) A[a++] = i;
                else B[b++] = i;
            }

            score = 0;
            int Ascore = Permutation(A, new boolean[src.length / 2], new int[2], 0);
            score = 0;
            int Bscore = Permutation(B, new boolean[src.length / 2], new int[2], 0);

            min = Math.min(min, Math.abs(Ascore - Bscore));
            return;
        }

        if (idx == src.length) return;

        src[idx] = true;
        Combination(src, idx + 1, cnt + 1);
        src[idx] = false;
        Combination(src, idx + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        factorial = 1;
        for (int i = 1; i <= n; i++) factorial *= i;

        Combination(new boolean[n], 0, 0);

        bw.write(min + "");
        bw.close();
    }
}