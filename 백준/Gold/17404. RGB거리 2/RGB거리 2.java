import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3]; // 각각의 집을 R, G, B로 칠하는 비용

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int INF = 10000000;
        int minCost = INF; // 모든 집을 칠하는 비용의 최솟값

        for (int firstColor = 0; firstColor < 3; firstColor++) { // 0번째 집이 R일 때, G일 때, B일 때
            int[][] dp = new int[n][3];

            for (int i = 0; i < 3; i++) {
                if (i == firstColor) dp[0][i] = arr[0][i]; // 0번째 집을 firstColor로 칠하기
                else dp[0][i] = INF; // 0번째 집을 firstColor가 아닌 색으로 칠하지 못하게 하기
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = arr[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]); // i번째 집을 R로 칠할 때의 최소 누적 비용
                dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]); // i번째 집을 G로 칠할 때의 최소 누적 비용
                dp[i][2] = arr[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]); // i번째 집을 B로 칠할 때의 최소 누적 비용
            }

            for (int i = 0; i < 3; i++) {
                if (i == firstColor) continue; // 0번째 집의 색과 n-1번째 집의 색은 달라야 함
                minCost = Math.min(minCost, dp[n-1][i]); // 더 작은 최소 누적 비용이 있다면 갱신
            }

        }
        bw.write(minCost + "");
        bw.close();
    }
}