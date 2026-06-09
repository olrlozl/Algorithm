import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        for (int[] r : road) {
            graph[r[0]].add(new Edge(r[1], r[2]));
            graph[r[1]].add(new Edge(r[0], r[2]));
        }
        
        int[] dist = Dijkstra(graph, N);
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }
        
        return answer;
    }
    
    public int[] Dijkstra(ArrayList<Edge>[] graph, int N) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        boolean[] visit = new boolean[N + 1];
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if (visit[now.v]) continue;
            visit[now.v] = true;
            
            for (Edge next: graph[now.v]) {
                if (dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = dist[now.v] + next.cost;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }
    
    public class Edge implements Comparable<Edge>{
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
}