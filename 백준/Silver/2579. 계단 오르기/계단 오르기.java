import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 2];
        for (int i = 2; i <= n + 1; i++) arr[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 2][2];
        for (int i = 2; i <= n + 1; i++) {
            dp[i][0] = arr[i] + dp[i-1][1];
            dp[i][1] = arr[i] + Math.max(dp[i-2][0], dp[i-2][1]);
        }

        bw.write(Math.max(dp[n + 1][0], dp[n + 1][1]) + " ");
        bw.close();
    }
}