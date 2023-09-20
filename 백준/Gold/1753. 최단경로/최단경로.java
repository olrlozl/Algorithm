import java.io.*;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int v;
        int cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static int[] Dijkstra (ArrayList<Edge>[] graph, int start, int n) {
        int[] dist = new int[n + 1]; // 출발지로부터의 거리 담을 배열
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(); // 우선순위 큐
        boolean[] visit = new boolean[n + 1]; // 방문 여부

        pq.add(new Edge(start, 0)); // 시작 정점을 우선순위 큐에 넣고
        dist[start] = 0; // 시작정점의 최단경로 0으로 설정

        while (!pq.isEmpty()) { // 큐가 빌 때 까지
            Edge now = pq.poll(); // 큐에서 정점 하나를 꺼내고

            if (visit[now.v]) continue; // 이미 방문한 정점이라면 넘어가기

            // 아직 방문하지 않은 정점이라면
            visit[now.v] = true; // 방문처리하고

            for (Edge next : graph[now.v]) { // 갈 수 있는 정점 중에서
                if (!visit[next.v]) { // 방문하지 않은 정점이 있다면
                    if (dist[next.v] > dist[now.v] + next.cost) { // 현재 정점을 거쳐서 가는 거리가 이미 보유하고 있는 거리보다 짧다면
                        dist[next.v] = dist[now.v] + next.cost; // 거리 최소값 갱신
                        pq.add(new Edge(next.v, dist[next.v])); // 우선순위 큐에 넣기
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        int start = Integer.parseInt(br.readLine()); // 시작 정점

        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        int[] dist = Dijkstra(graph, start, n);

        for (int i = 1; i <= n; i++) {
            bw.write(dist[i] == Integer.MAX_VALUE ? "INF\n" : dist[i] + "\n");
        }

        bw.close();
    }
}