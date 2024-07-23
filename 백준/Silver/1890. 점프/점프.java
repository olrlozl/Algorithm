import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        long[][] dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (dp[r][c] == 0) continue;
                int num = arr[r][c];
                if (num == 0) continue;
                if (r + num < N) dp[r + num][c] += dp[r][c];
                if (c + num < N) dp[r][c + num] += dp[r][c];
            }
        }

        bw.write(dp[N - 1][N - 1] + "");
        bw.close();
    }
}