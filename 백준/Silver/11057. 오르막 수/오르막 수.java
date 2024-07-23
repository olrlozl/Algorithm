import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10]; // dp[i][j] = j로 시작하는 i+1 자리수의 개수

        for (int i = 0; i <= 9; i++) dp[0][i] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 9; j >= 0; j--) {
                dp[i][j] = (dp[i - 1][j] + (j == 9 ? 0 : dp[i][j + 1])) % 10007;
            }
        }

        bw.write(dp[N][0] % 10007+ "");
        bw.close();
    }
}