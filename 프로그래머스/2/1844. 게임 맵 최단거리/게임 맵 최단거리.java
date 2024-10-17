import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[maps.length][maps[0].length];
        
        queue.add(new Point(0, 0));
        visit[0][0] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == maps.length - 1 && now.y == maps[0].length - 1) {
                return maps[maps.length - 1][maps[0].length - 1];
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.x + delta[i][0];
                int nc = now.y + delta[i][1];
                if (0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length 
                    && maps[nr][nc] != 0 && !visit[nr][nc]) {
                    queue.add(new Point(nr, nc));
                    visit[nr][nc] = true;
                    maps[nr][nc] = maps[now.x][now.y] + 1;
                }
            }
        }
        return -1;
    }
}