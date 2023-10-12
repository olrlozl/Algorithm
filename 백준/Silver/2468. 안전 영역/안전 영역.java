import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int maxCnt = 1;

    public static void bfs(boolean[][] sink, boolean[][] visit, int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c));
        visit[r][c] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.x + delta[d][0];
                int nc = now.y + delta[d][1];
                if (0 > nr || nr >= n || 0 > nc || nc >= n || sink[nr][nc] || visit[nr][nc]) continue;
                queue.add(new Point(nr, nc));
                visit[nr][nc] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, arr[i][j]);
            }
        }

        boolean[][] sink = new boolean[n][n];

        for (int rain = 1; rain <= maxHeight; rain++) {
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (arr[i][j] <= rain) sink[i][j] = true;

            int cnt = 0;
            boolean[][] visit = new boolean[n][n];

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (!sink[r][c] && !visit[r][c]) {
                        bfs(sink, visit, r, c);
                        cnt++;
                    }
                }
            }

            maxCnt = Math.max(maxCnt, cnt);
        }

        bw.write(maxCnt + "");
        bw.close();

    }
}