import java.util.*;
import java.awt.*;

class Solution {
    public int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int solution(int[][] maps) {
        
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(0, 0));
        maps[0][0]++;
        
        loop: while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.x + delta[d][0];
                int nc = now.y + delta[d][1];
                if (0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length && maps[nr][nc] == 1) {
                    queue.add(new Point(nr, nc));
                    maps[nr][nc] += maps[now.x][now.y];
                    if (nr == maps.length - 1 && nc == maps[0].length - 1) break loop;
                }
            }
        }
        
        if (maps[maps.length-1][maps[0].length-1] == 1) return -1;
        return maps[maps.length-1][maps[0].length-1] - 1;
    }
}