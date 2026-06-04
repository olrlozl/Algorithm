import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int n, int[][] costs) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        kruskal(costs, parent);

        return answer;
    }
    
    public int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
    
    public void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
    
    public void kruskal(int[][] costs, int[] parent) {
        for (int[] c : costs) {
            if (find(parent, c[0]) != find(parent, c[1])) {
                union(parent, c[0], c[1]);
                answer += c[2];
            } 
        }
    }
}