import java.io.*;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int v;
        int cost;

        public Edge (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void Dijkstra(ArrayList<Edge>[] graph, int[] dist, int n, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n + 1];

        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visit[now.v]) continue;

            visit[now.v] = true;

            for (Edge next : graph[now.v]) {
                if (!visit[next.v] && dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = dist[now.v] + next.cost;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        int[][] table = new int[n][n];
        int[] dist = new int[n + 1];

        for (int start = 1; start <= n; start++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            Dijkstra(graph, dist, n, start);

            for (int end = 1; end <= n; end++) {
                table[start - 1][end - 1] = dist[end];
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (table[i][j] != Integer.MAX_VALUE && table[j][i] != Integer.MAX_VALUE) {
                        min = Math.min(min, table[i][j] + table[j][i]);
                    }
                }
            }
        }

        bw.write((min == Integer.MAX_VALUE ? -1 : min) + "");
        bw.close();
    }
}