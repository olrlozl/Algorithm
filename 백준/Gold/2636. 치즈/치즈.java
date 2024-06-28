import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int n; // 세로
    public static int m; // 가로
    public static int[][] board;
    public static int cheese; // 치즈 개수
    public static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void bfs (boolean[][] visit, Point p) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(p.x, p.y));
        visit[p.x][p.y] = true;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.x + delta[i][0];
                int nc = cur.y + delta[i][1];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visit[nr][nc]) {
                    if (board[nr][nc] == 0) {
                        queue.add(new Point(nr, nc));
                        visit[nr][nc] = true;
                    } else if (board[nr][nc] == 1) {
                        board[nr][nc] = 0;
                        visit[nr][nc] = true;
                        cheese--;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로
        board = new int[n][m];
        cheese = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int previous_cheese = cheese;
        int time = 0;

        while (cheese > 0) {
            boolean[][] visit = new boolean[n][m];
            bfs(visit, new Point(0, 0));
            time++;
            if (cheese > 0) {
                previous_cheese = cheese;
            }
        }

        bw.write(time + "\n");
        bw.write(previous_cheese + "");
        bw.close();
    }
}