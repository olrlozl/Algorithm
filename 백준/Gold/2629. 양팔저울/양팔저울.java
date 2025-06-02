import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] weight;
    public static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 추 개수
        weight = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][15001];
        func(0, 0);

        int M = Integer.parseInt(br.readLine()); // 구슬 개수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            bw.write((x > 15000 ? "N" : (dp[N][x] ? "Y" : "N")) + " ");
        }
        bw.close();
    }

    public static void func(int idx, int num) {
        if (dp[idx][num]) return;
        dp[idx][num] = true;
        if (idx == N) return;

        func(idx + 1, num);
        func(idx + 1, num + weight[idx + 1]);
        func(idx + 1, Math.abs(num - weight[idx + 1]));
    }
}