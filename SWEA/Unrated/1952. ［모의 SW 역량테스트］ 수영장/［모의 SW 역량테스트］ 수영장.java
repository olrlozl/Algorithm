import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int cost = 0;

            st = new StringTokenizer(br.readLine());
            int[] price = new int[4]; // 1일, 1달, 3달, 1년 이용권 가격
            for (int i = 0; i < 4; i++) price[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] plan = new int[13]; // 1~12월 이용 계획
            for (int i = 1; i <= 12; i++) plan[i] = Integer.parseInt(st.nextToken());

            int[] dp = new int[13]; // 1~12월 이용권 가격의 최소의 누적합

            for (int i = 1; i <= 12; i++) {
                // 1일 이용권
                dp[i] = dp[i-1] + plan[i] * price[0];
                // 1달 이용권
                if (dp[i] > dp[i-1] + price[1]) dp[i] = dp[i-1] + price[1];
                // 3달 이용권
                if (i >= 3) if (dp[i] > dp[i-3] + price[2]) dp[i] = dp[i-3] + price[2];
                // 1년 이용권
                if (i == 12) if (dp[i] > price[3]) dp[i] = price[3];
            }
            bw.write("#" + t + " " + dp[12] + "\n");
        }
        bw.close();
    }
}