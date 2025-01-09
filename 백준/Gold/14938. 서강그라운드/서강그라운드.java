import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 지역 개수
        int m = Integer.parseInt(st.nextToken()); // 수색 범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        int[] items = new int[n + 1]; // 각 지역의 아이템 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Edge>[] graph = new ArrayList[n + 1]; // 각 지역의 (이웃 지역과 거리)
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        int maxItem = 0;
        for (int i = 1; i <= n; i++) {
            int[] dist = Dijkstra(graph, n, i); // i지역에서 출발했을 때 각 지역까지의 최소거리
            int item = 0;
            for (int j = 1; j <= n; j++) { // j 지역이
                if (dist[j] <= m) { // 수색범위에 있을 경우
                    item += items[j]; // 득템
                }
            }
            maxItem = Math.max(maxItem, item);
        }

        bw.write(maxItem + "");
        bw.close();
    }

    public static class Edge implements Comparable<Edge>{
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

    public static int[] Dijkstra(ArrayList<Edge>[] graph, int n, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        boolean[] visit = new boolean[n + 1];

        pq.add(new Edge(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if (visit[now.idx]) continue;
            visit[now.idx] = true;

            for (Edge next: graph[now.idx]) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }
        return dist;
    }
}