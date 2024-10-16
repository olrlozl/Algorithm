import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[] street = br.readLine().toCharArray();

        int[] dp = new int[N];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int now = 0; now < N; now++) {
            for (int next = now + 1; next < N; next++) {
                if (street[now] == 'B' && street[next] == 'O') {
                    dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                } else if (street[now] == 'O' && street[next] == 'J') {
                    dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                } else if (street[now] == 'J' && street[next] == 'B') {
                    dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                }
            }
        }

        bw.write((dp[N - 1] == 987654321 ? "-1" : dp[N - 1]) + "");
        bw.close();
    }
}