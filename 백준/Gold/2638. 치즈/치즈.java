import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int n; // 세로
    public static int m; // 가로
    public static int[][] board;
    public static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void bfs (Point p) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];

        queue.add(new Point(p.x, p.y));
        visit[p.x][p.y] = true;
        board[p.x][p.y] = 2;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.x + delta[i][0];
                int nc = cur.y + delta[i][1];
                if (0 <= nr && nr < n && 0 <= nc && nc < m
                        && !visit[nr][nc] && (board[nr][nc] == 0 || board[nr][nc] == 2)) {
                    board[nr][nc] = 2;
                    queue.add(new Point(nr, nc));
                    visit[nr][nc] = true;
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
        int cheese = 0; // 치즈 개수
        int time = 0; // 치즈가 모두 녹는데 걸리는 시간

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cheese++;
                }
            }
        }

        while (cheese > 0) {
            bfs(new Point(0,0)); // 외부공기 2로 변경

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {

                    if (board[r][c] == 1) {
                        int outAir = 0;

                        for (int d = 0; d < 4; d++) {
                            int nr = r + delta[d][0];
                            int nc = c + delta[d][1];
                            if (0 <= nr && nr < n && 0 <= nc && nc < m
                                    && board[nr][nc] == 2) {
                                outAir++;
                            }
                        }

                        if (outAir >= 2) {
                            board[r][c] = 0;
                            cheese--;
                        }
                    }
                }
            }
            time++;
        }

        bw.write(time + "\n");
        bw.close();
    }
}