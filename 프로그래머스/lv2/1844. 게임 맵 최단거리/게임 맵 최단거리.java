import java.awt.*;
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        // 하 우 상 좌
        int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        //// bfs
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(0, 0)); // 시작 노드를 큐에 넣기

        loop: while (!queue.isEmpty()) { // 큐가 빌 때 까지
            Point p = queue.poll(); // 큐에서 원소 하나를 꺼내고
            for (int d = 0; d < 4; d++) {
                int nr = p.x + delta[d][0];
                int nc = p.y + delta[d][1];
                // 이웃한 노드 중에서 방문하지 않은 길이 있다면
                if (0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length && maps[nr][nc] == 1 && !(nr == 0 && nc == 0)) {
                    queue.add(new Point(nr, nc)); // 큐에 넣고
                    maps[nr][nc] = maps[p.x][p.y] + 1; // 방문 처리
                    if (nr == maps.length - 1 && nc == maps[0].length - 1) break loop; // 상대팀 진영에 도착하면 끝
                }
            }
        }
        int result = maps[maps.length-1][maps[0].length-1] == 1 ? -1: maps[maps.length-1][maps[0].length-1];
        return result;
    }
}