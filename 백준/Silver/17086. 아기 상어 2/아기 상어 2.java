import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

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
            }
        }

        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxDist = Math.max(maxDist, bfs(i, j));
            }
        }

        bw.write(maxDist + "");
        bw.close();
    }

    public static int bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        int dist = 0;

        queue.add(new Point(r, c));
        visit[r][c] = true;

        while(!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Point now = queue.poll();
                if (arr[now.x][now.y] == 1) return dist;

                for (int d = 0; d < 8; d++) {
                    int nr = now.x + delta[d][0];
                    int nc = now.y + delta[d][1];

                    if (0 <= nr && nr < N && 0 <= nc && nc < M && !visit[nr][nc]) {
                        queue.add(new Point(nr, nc));
                        visit[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        return dist;
    }
}