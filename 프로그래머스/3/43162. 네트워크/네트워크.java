import java.util.*;

class Solution {
    public boolean[] visit;
    public ArrayList<Integer>[] graph;
    
    public int solution(int n, int[][] computers) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (computers[i][j] == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        visit = new boolean[n];
        int cnt = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                bfs(i);
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        visit[start] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            for (int next: graph[now]) {
                if (!visit[next]) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }
    }
}