import java.util.*;

class Solution {
    public Queue<Integer> queue = new LinkedList<Integer>();
    public HashSet<Integer> visit = new HashSet<>();
    
    public int bfs(int x, int y, int n) {
        queue.add(x);
        visit.add(x);
        int cnt = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == y) {
                    return cnt;
                }
                addQueue(cur + n, y);
                addQueue(cur * 2, y);
                addQueue(cur * 3, y);
            }
            
            cnt++;
        }
        
        return -1;
    }
    
    public void addQueue(int a, int y) {
        if (a <= y && !visit.contains(a)) {
            queue.add(a);
            visit.add(a);
        }
    }
    
    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }
}