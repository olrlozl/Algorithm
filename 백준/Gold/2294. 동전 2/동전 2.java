import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전 종류
        int k = Integer.parseInt(st.nextToken()); // 원하는 가치 합

        int[] coins = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = k + 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j < coins[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                }
            }
        }

        bw.write((dp[n][k] == k + 1 ? -1 : dp[n][k]) + "");
        bw.close();
    }
}