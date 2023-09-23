import java.util.*;

class Solution {
    public static void dfs (ArrayList<Integer>[] graph, boolean[] visit, int now) {
        visit[now] = true;

        for (int next : graph[now]) {
            if (!visit[next]) {
                dfs(graph, visit, next);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph[i].add(j);
                }
            }
        }

        boolean[] visit = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(graph, visit, i);
                count++;
            }
        }

        return count;
    }
}