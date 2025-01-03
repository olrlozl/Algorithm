import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int N;
    public static int M;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동 서 남 북
    public static int cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int time = 0;

        while (cheese > 0) {
            bfs();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1) {
                        tryMelt(i, j);
                    }
                }
            }
            time++;
        }

        bw.write(time + "");
        bw.close();
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        queue.add(new Point(0, 0));
        arr[0][0] = 2;
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now.x + delta[i][0];
                int nc = now.y + delta[i][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < M && !visit[nr][nc] && (arr[nr][nc] == 0 || arr[nr][nc] == 2)) {
                    queue.add(new Point(nr, nc));
                    visit[nr][nc] = true;
                    arr[nr][nc] = 2;
                }
            }
        }
    }

    public static void tryMelt(int r, int c) {
        int outline = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (arr[nr][nc] == 2) {
                outline++;
                if (outline >= 2) {
                    arr[r][c] = 0;
                    cheese--;
                    return;
                }
            }
        }
    }
}