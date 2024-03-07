import java.util.*;
import java.awt.*;

class Solution {
    public int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하 우 상 좌
    
    public void bfs(int[][] maps, Point start) {
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(start);
        maps[start.x][start.y]++;
        
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = point.x + delta[i][0];
                int nc = point.y + delta[i][1];
                
                if (0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length 
                    && maps[nr][nc] == 1) {
                    queue.add(new Point(nr, nc));
                    maps[nr][nc] += maps[point.x][point.y];
                    
                    if (nr == maps.length && nc == maps[0].length) return;
                }
            }
        }
    }
    
    public int solution(int[][] maps) {
        bfs(maps, new Point(0, 0));
        
        if (maps[maps.length - 1][maps[0].length - 1] == 1) return -1;
        return maps[maps.length - 1][maps[0].length - 1] - 1;
    }
}