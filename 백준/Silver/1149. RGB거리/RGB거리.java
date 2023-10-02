import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
                dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
                dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }

        bw.write(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]) + "");
        bw.close();
    }
}