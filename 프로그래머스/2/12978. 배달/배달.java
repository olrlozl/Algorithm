import java.util.*;

class Solution {
    public int cnt = 0;
    
    public int solution(int N, int[][] road, int K) {
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        
        for (int[] r : road) {
            graph[r[0]].add(new Edge(r[1], r[2]));
            graph[r[1]].add(new Edge(r[0], r[2]));
        }
        
        Dijkstra(graph, N, K);
        
        return cnt;
    }
    
    public class Edge implements Comparable<Edge> {
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
    
    public void Dijkstra(ArrayList<Edge>[] graph, int N, int K) {
        boolean[] visit = new boolean[N + 1];
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        
        while(!pq.isEmpty()) {
            int nowIdx = pq.poll().idx;
            if (visit[nowIdx]) continue;
            visit[nowIdx] = true;
            
            for (Edge next: graph[nowIdx]) {
                if (dist[next.idx] > dist[nowIdx] + next.cost) {
                    dist[next.idx] = dist[nowIdx] + next.cost;
                    pq.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                cnt++;
            }
        }
    }
}