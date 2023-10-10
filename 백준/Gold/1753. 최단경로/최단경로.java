import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int v;
        int cost;

        public Edge (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo (Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void Dijkstra (ArrayList<Edge>[] graph, int[] dist, int n, int k) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n + 1];

        pq.add(new Edge(k, 0));
        dist[k] = 0;

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
        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        int k = Integer.parseInt(br.readLine()); // 시작 정점

        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Dijkstra(graph, dist, n, k);

        for (int i = 1; i <= n; i++) {
            bw.write((dist[i] != Integer.MAX_VALUE ? dist[i] : "INF") + "\n");
        }
        bw.close();
    }
}