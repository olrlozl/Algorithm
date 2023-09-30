import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static int[][] delta = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
    public static int maxCnt = Integer.MIN_VALUE;

    public static int bfs (Point start, boolean[][] visit) {
        int virus = 0;
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.x + delta[d][0];
                int nc = now.y + delta[d][1];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && arr[nr][nc] == 0 && !visit[nr][nc]) {
                    queue.add(new Point(nr, nc));
                    visit[nr][nc] = true;
                    virus++;
                }
            }
        }
        return virus;
    }

    public static void Combination(ArrayList<Point> src, Point[] tgt, int srcIdx, int tgtIdx) {
        if (tgtIdx == 3) {
            for (int i = 0; i < 3; i++) arr[tgt[i].x][tgt[i].y] = 1;

            boolean[][] visit = new boolean[n][m];
            for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) if (arr[i][j] != 0) visit[i][j] = true;

            int cnt = src.size() - 3;

            for (int r = 0; r < n; r++) for (int c = 0; c < m; c++) if (arr[r][c] == 2) cnt -= bfs(new Point(r, c), visit);

            maxCnt = Math.max(cnt, maxCnt);

            for (int i = 0; i < 3; i++) arr[tgt[i].x][tgt[i].y] = 0;

            return;
        }

        if (srcIdx == src.size()) return;

        tgt[tgtIdx] = src.get(srcIdx);
        Combination(src, tgt, srcIdx + 1, tgtIdx + 1);
        Combination(src, tgt, srcIdx + 1, tgtIdx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Point> src = new ArrayList<>();
        Point[] tgt = new Point[3];

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) src.add(new Point(i, j));
            }
        }

        Combination(src, tgt, 0, 0);

        bw.write(maxCnt + "");
        bw.close();
    }
}