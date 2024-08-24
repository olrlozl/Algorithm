import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    public static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return  this.cost - o.cost;
        }
    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visit[cur.idx]) continue;
            visit[cur.idx] = true;

            for (Edge next : graph[cur.idx]) {
                if (dist[next.idx] > cur.cost + next.cost) {
                    dist[next.idx] = cur.cost + next.cost;
                    pq.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시 수
        int M = Integer.parseInt(br.readLine()); // 버스 수

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); // 출발 도시 번호
            int to = Integer.parseInt(st.nextToken()); // 도착 도시 번호
            int cost = Integer.parseInt(st.nextToken()); // 버스 비용
            graph[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = dijkstra(graph, start);
        bw.write(dist[end] + "");
        bw.close();
    }
}