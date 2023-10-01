import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int minTime = Integer.MAX_VALUE;

    public static void bfs(Point[] tgt, boolean[][] visit) {
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.add(tgt[i]);
            visit[tgt[i].x][tgt[i].y] = true;
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            time++;
            for (int i = 0; i < len; i++) {
                Point now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.x + delta[d][0];
                    int nc = now.y + delta[d][1];
                    if (0 <= nr && nr < n && 0 <= nc && nc < n && !visit[nr][nc] && arr[nr][nc] != 1) {
                        queue.add(new Point(nr, nc));
                        visit[nr][nc] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (!visit[i][j]) return;
        minTime = Math.min(minTime, time - 1);
    }

    public static void Combination(ArrayList<Point> two, Point[] tgt, int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            boolean[][] visit = new boolean[n][n];
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (arr[i][j] == 1) visit[i][j] = true;
            bfs(tgt, visit);
            return;
        }

        if (srcIdx == two.size()) return;

        tgt[tgtIdx] = two.get(srcIdx);
        Combination(two, tgt, srcIdx + 1, tgtIdx + 1);
        Combination(two, tgt, srcIdx + 1, tgtIdx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        ArrayList<Point> two = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) two.add(new Point(i, j));
            }
        }

        Point[] tgt = new Point[m];
        Combination(two, tgt, 0, 0);

        bw.write((minTime == Integer.MAX_VALUE ? -1 : minTime) + "");
        bw.close();
    }
}