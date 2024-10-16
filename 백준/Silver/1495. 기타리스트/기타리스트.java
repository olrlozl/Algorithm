import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 곡의 개수
        int S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        int M = Integer.parseInt(st.nextToken()); // 최대 볼륨

        int[] V = new int[N]; // i번째 곡 연주 전 바꿀 수 있는 볼륨
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i][j]) {
                    if (j + V[i] <= M) dp[i + 1][j + V[i]] = true;
                    if (j - V[i] >= 0) dp[i + 1][j - V[i]] = true;
                }
            }
        }

        int maxL = -1;
        for (int j = M; j >= 0; j--) {
            if (dp[N][j]) {
                maxL = j;
                break;
            }
        }

        bw.write(maxL + "");
        bw.close();
    }
}