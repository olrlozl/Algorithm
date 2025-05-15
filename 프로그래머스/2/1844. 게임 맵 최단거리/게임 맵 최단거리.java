import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == maps.length - 1 && now.y == maps[0].length - 1) {
                return maps[maps.length - 1][maps[0].length - 1];
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.x + delta[i][0];
                int nc = now.y + delta[i][1];
                if (0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length 
                    && maps[nr][nc] == 1) {
                    queue.add(new Point(nr, nc));
                    maps[nr][nc] = maps[now.x][now.y] + 1;
                }
            }
        }
        return -1;
    }
}