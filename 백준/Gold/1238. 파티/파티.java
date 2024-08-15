import java.io.*;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static int[] Dijkstra(ArrayList<Edge>[] graph, int N, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean[] visit = new boolean[N + 1];

        while(!pq.isEmpty()) {
            int nowIdx = pq.poll().idx;

            if (visit[nowIdx]) continue;
            visit[nowIdx] = true;

            for (Edge next : graph[nowIdx]) {
                if (dist[next.idx] > dist[nowIdx] + next.cost) {
                    dist[next.idx] = dist[nowIdx] + next.cost;

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

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수, 마을 수
        int M = Integer.parseInt(st.nextToken()); // 단방향 도로 수
        int X = Integer.parseInt(st.nextToken()); // 파티가 열리는 마을

        ArrayList<Edge>[] r_graph = new ArrayList[N + 1];
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            r_graph[i] = new ArrayList<>();
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken()); // 도로 시작점
            int from = Integer.parseInt(st.nextToken()); // 도로 도착점
            int t = Integer.parseInt(st.nextToken()); // 소요시간
            r_graph[to].add(new Edge(from, t)); // 역방향 그래프
            graph[from].add(new Edge(to, t)); // 순방향 그래프
        }

        int[] go = Dijkstra(r_graph, N, X); // 각 마을에서 출발하여 X 마을까지 가는 최소 거리
        int[] back = Dijkstra(graph, N, X); // X 마을에서 출발하여 각 마을까지 가는 최소거리

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, go[i] + back[i]);
        }

        bw.write(max + "");
        bw.close();
    }
}