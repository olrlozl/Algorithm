import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int n, int[][] costs) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int[] c : costs) {
            graph[c[0]].add(new Edge(c[1], c[2]));
            graph[c[1]].add(new Edge(c[0], c[2]));
        }
        
        prim(graph, 0, n);
        return answer;
    }
    
    public class Edge implements Comparable<Edge> {
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
    
    public void prim(ArrayList<Edge>[] graph, int start, int n) {
        boolean[] visit = new boolean[n];
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if (visit[now.v]) continue;
            visit[now.v] = true;
            answer += now.cost; //
            
            for (Edge next : graph[now.v]) {
                if (!visit[next.v]) { //
                    pq.add(next);
                }
            }
        }
    }
}