import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        int K = Integer.parseInt(br.readLine()); // 시작 정점

        ArrayList<Edge>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Dijkstra(graph, dist, V, K);

        for (int i = 1; i <= V; i++) {
            bw.write((dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]) + "\n");
        }
        bw.close();
    }

    public static class Edge implements Comparable<Edge> {
        int v;
        int cost;

        public Edge(int idx, int cost) {
            this.v = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void Dijkstra (ArrayList<Edge>[] graph, int[] dist, int V, int K) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[V + 1];

        pq.add(new Edge(K, 0));
        dist[K] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!visit[now.v]) {
                visit[now.v] = true;
                for (Edge next : graph[now.v]) {
                    if (!visit[next.v] && dist[next.v] > dist[now.v] + next.cost) {
                        dist[next.v] = dist[now.v] + next.cost;
                        pq.add(new Edge(next.v, dist[next.v]));
                    }
                }
            }
        }
    }
}