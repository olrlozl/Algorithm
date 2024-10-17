import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};
    public static ArrayList<Point> sharks;
    public static int maxDist = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        sharks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    sharks.add(new Point(i, j));
                }
            }
        }

        bfs();

        bw.write(maxDist + "");
        bw.close();
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.addAll(sharks);

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 8; d++) {
                int nr = now.x + delta[d][0];
                int nc = now.y + delta[d][1];

                if (0 <= nr && nr < N && 0 <= nc && nc < M
                        && (arr[nr][nc] == 0 || arr[nr][nc] > arr[now.x][now.y] + 1)) {
                    queue.add(new Point(nr, nc));
                    arr[nr][nc] = arr[now.x][now.y] + 1;
                    maxDist = Math.max(maxDist, arr[nr][nc] - 1);
                }
            }
        }
    }
}