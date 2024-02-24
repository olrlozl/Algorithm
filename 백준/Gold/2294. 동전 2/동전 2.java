import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전 종류 가짓수
        int k = Integer.parseInt(st.nextToken()); // 동전 가치 합
        
        int[] coins = new int[n];
        for (int c = 0; c < n; c++) coins[c] = Integer.parseInt(br.readLine());
        
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;
        
        for (int c = 0; c < n; c++) {
            for (int i = coins[c]; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coins[c]] + 1);
            }
        }
        
        bw.write((dp[k] == 987654321 ? -1 : dp[k]) + "");
        bw.close();
    }
}