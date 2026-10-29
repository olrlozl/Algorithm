import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        maps[0][0]++;
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == N - 1 && now.y == M - 1) {
                return maps[N-1][M-1] - 1;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && maps[nx][ny] == 1) {
                    queue.add(new Point(nx, ny));
                    maps[nx][ny] = maps[now.x][now.y] + 1;
                }
            }
        }
        
        return -1;
    }
}