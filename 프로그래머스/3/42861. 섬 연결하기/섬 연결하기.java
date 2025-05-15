import java.util.*;

class Solution {
    public int result = 0;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        Kruskal(n, costs);
        return result;
    }
    
    public void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
    
    public int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return find(parent, parent[x]);
    }
    
    public void Kruskal(int n, int[][] costs) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int[] cost: costs) {
            if (find(parent, cost[0]) != find(parent, cost[1])) {
                union(parent, cost[0], cost[1]);
                result += cost[2];
            }
        }
    }
}