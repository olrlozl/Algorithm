import java.io.*;
import java.util.*;

public class Main {
    public static int m;
    public static int n;
    public static int h;
    public static int[][][] arr;
    public static ArrayList<point> ripe;
    public static int notripeCnt;
    public static int result = 0;
    public static int[][] delta = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0 ,-1, 0}, {0, 0, 1}, {0, 0, -1}};
    public static class point {
        int a;
        int b;
        int c;

        public point(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void bfs() {
        Queue<point> queue = new LinkedList<>();
        int[][][] visit = new int[h][n][m];

        for (int i = 0; i < ripe.size(); i++) {
            queue.add(new point(ripe.get(i).a, ripe.get(i).b, ripe.get(i).c));
        }

        int day = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                point now = queue.poll();
                for (int d = 0; d < 6; d++) {
                    int na = now.a + delta[d][0];
                    int nb = now.b + delta[d][1];
                    int nc = now.c + delta[d][2];
                    if (0 > na || na >= h || 0 > nb || nb >= n || 0 > nc || nc >= m || arr[na][nb][nc] != 0 || visit[na][nb][nc] != 0) continue;
                    queue.add(new point(na, nb, nc));
                    visit[na][nb][nc] = visit[now.a][now.b][now.c] + 1;
                    notripeCnt--;
                }
            }
            if (!queue.isEmpty()) day++;
        }

        result = (notripeCnt == 0 ? day : -1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로
        h = Integer.parseInt(st.nextToken()); // 높이

        arr = new int[h][n][m];

        ripe = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) ripe.add(new point(i, j, k));
                    else if (arr[i][j][k] == 0) notripeCnt++;
                }
            }
        }

        if (ripe.size() != m * n * h) bfs();

        bw.write(result + "");
        bw.close();

    }
}