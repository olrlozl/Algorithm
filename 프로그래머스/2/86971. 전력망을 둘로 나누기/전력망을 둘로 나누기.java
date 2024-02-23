import java.util.*;

class Solution {
    public int bfs(ArrayList<Integer>[] graph, boolean[] visit, int start) {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        visit[start] = true;
        queue.add(start);
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            cnt++;
            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < wires.length; i++) {
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < wires.length; i++) {
            boolean[] visit = new boolean[n + 1];
            visit[wires[i][1]] = true;
            min = Math.min(min, Math.abs(n - 2 * bfs(graph, visit, wires[i][0])));
        }
        return min;
    }
}