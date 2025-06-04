import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int cnt = 0;
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];
        
        queue.add(1);
        visit[1] = true;
        
        while(!queue.isEmpty()) {
            int len = queue.size();
            cnt = len;
            
            for (int i = 0; i < len; i++) {
                int now = queue.poll();
            
                for (int next : graph[now]) {
                    if (!visit[next]) {
                        queue.add(next);
                        visit[next] = true;
                    }
                }
            }
        }
        
        return cnt;
    }
}