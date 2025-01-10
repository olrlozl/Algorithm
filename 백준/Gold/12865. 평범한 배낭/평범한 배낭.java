import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 물건 개수
        int k = Integer.parseInt(st.nextToken()); // 가방에 담을 수 있는 최대 무게

        int[] weight = new int[n]; // 물건 무게
        int[] cost = new int[n]; // 물건 가치

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken()); // 물건 무게
            cost[i] = Integer.parseInt(st.nextToken()); // 물건 가치
        }

        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= weight[i]; j--) { // 뒤에서부터 순회해야함 (앞에서부터 갱신하면, 이미 갱신된 값이 뒤 계산에 영항 미침)
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + cost[i]);
            }
        }

        bw.write(dp[k] + "");
        bw.close();
    }
}