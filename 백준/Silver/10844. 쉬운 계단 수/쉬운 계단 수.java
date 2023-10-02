import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n][12];

        for (int i = 1; i <= 10; i++) dp[0][i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 10; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }

        long sum = 0;
        for (int i = 2; i <= 10; i++) sum += dp[n - 1][i];

        bw.write(sum % 1000000000 + "");
        bw.close();
    }
}