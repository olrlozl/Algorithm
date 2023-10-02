import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][2];

        dp[1][0] = arr[1];
        dp[1][1] = arr[1];

        int max = dp[1][0];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), arr[i] + dp[i - 1][1]);
            dp[i][1] = arr[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        bw.write(max + "");
        bw.close();
    }
}