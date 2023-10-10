import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {1, 1}, {1, 0}}; // →, ↘, ↓
    public static int cnt = 0; // 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수

    public static void dfs(boolean[][] visit, Point head, int state) {
        if (head.x == n - 1 && head.y == n - 1) {
            cnt++;
            return;
        }

        visit[head.x][head.y] = true;

        for (int d = 0; d < 3; d++) {
            if (state == 0 && d == 2) continue;
            if (state == 2 && d == 0) continue;
            int nr = head.x + delta[d][0];
            int nc = head.y + delta[d][1];
            if (0 > nr || nr >= n || 0 > nc || nc >= n || visit[nr][nc] || arr[nr][nc] == 1) continue;
            if (d == 1 && (arr[nr - 1][nc] == 1 || arr[nr][nc - 1] == 1)) continue;

            dfs(visit, new Point(nr, nc), d);
            visit[nr][nc] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Point head = new Point(0,1);
        boolean[][] visit = new boolean[n][n];

        dfs(visit, head, 0);

        bw.write(cnt + "");
        bw.close();
    }
}