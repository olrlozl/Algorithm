import java.util.*;

class Solution {
    public void bfs(ArrayList<Integer>[] graph, boolean[] visit, int x) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(x);
        visit[x] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (!visit[next]) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph[i + 1].add(j + 1);
                }
            }
        }
        
        boolean[] visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                bfs(graph, visit, i);
                cnt++;
            }
        }
        
        return cnt;
    }
}