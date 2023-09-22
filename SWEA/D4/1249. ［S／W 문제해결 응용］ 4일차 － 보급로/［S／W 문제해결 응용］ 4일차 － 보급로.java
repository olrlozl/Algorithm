import java.io.*;
import java.util.*;

public class Solution {
    public static class Edge implements Comparable<Edge> {
        int r;
        int c;
        int cost;

        public Edge (int x, int y, int cost){
            this.r = x;
            this.c = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

    }
    public static int Dijkstra(int[][] arr, int N) {
        int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}}; // 하 우 좌 상

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][N];

        pq.add(new Edge(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visit[now.r][now.c]) continue;

            visit[now.r][now.c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + delta[d][0];
                int nc = now.c + delta[d][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && !visit[nr][nc]) {
                    if (dist[nr][nc] > dist[now.r][now.c] + arr[nr][nc]) {
                        dist[nr][nc] = dist[now.r][now.c] + arr[nr][nc];
                        pq.add(new Edge(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                char[] charr = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = charr[j] - '0';
                }
            }
            bw.write("#" + t + " " + Dijkstra(arr, N)+ "\n");
        }
        bw.close();
    }
}