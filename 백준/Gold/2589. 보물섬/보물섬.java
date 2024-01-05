import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int h; // 세로
    public static int w; // 가로
    public static Character[][] arr; // 보물지도

    public static int maxTime = 0; // 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동할 때 최대시간
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];
        int depth = 0;

        queue.add(start); // 시작 정점을 큐에 넣고
        visit[start.x][start.y] = true; // 시작정점을 방문처리

        while (!queue.isEmpty()) { // 큐가 빌 때 까지
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                Point point = queue.poll(); // 큐에서 원소 하나를 꺼내고

                for (int d = 0; d < 4; d++) {
                    int nh = point.x + delta[d][0];
                    int nw = point.y + delta[d][1];

                    // 이웃한 육지 중에서 방문하지 않았다면
                    if (0 <= nh && nh < h && 0 <= nw && nw < w && arr[nh][nw] == 'L' && !visit[nh][nw]) {
                        queue.add(new Point(nh, nw)); // 큐에 넣고
                        visit[nh][nw] = true; // 방문처리
                    }
                }
            }
            depth++;
        }
        maxTime = Math.max(depth - 1, maxTime);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken()); // 세로
        w = Integer.parseInt(st.nextToken()); // 가로

        arr = new Character[h][w]; // 보물지도

        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == 'L') {
                    bfs(new Point(i, j));
                }
            }
        }
        bw.write(maxTime + "");
        bw.close();
    }
}