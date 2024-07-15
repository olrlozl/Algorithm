import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 도시 수
        long[] dist = new long[N - 1]; // 거리
        long[] cost = new long[N - 1]; // 비용

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        long minCost = cost[0];
        for (int i = 0; i < N - 1; i++) {
            minCost = Math.min(minCost, cost[i]);
            result += minCost * dist[i];
        }

        bw.write(result + "");
        bw.close();
    }
}