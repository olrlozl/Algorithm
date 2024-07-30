import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 1];
        int[] price = new int[N + 1];
        boolean[] consult = new boolean[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1]; // 전날까지의 최대이익 반영
            // 1 ~ i 일 미상담 건 중 i일에 종료되는 게 있다면
            for (int j = 1; j <= i; j++) {
                if (!consult[j] && i - j + 1 == time[j]) {
                    dp[i] = Math.max(dp[i], dp[i - time[j]] + price[j]);
                    consult[j] = true;
                }
            }
        }

        bw.write(dp[N] + "");
        bw.close();
    }
}