import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr, newArr;
    static int M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int sum, maxSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        M = (int) Math.pow(2, N);
        arr = new int[M][M];
        newArr = new int[M][M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int level = Integer.parseInt(st.nextToken());
            int len = (int) Math.pow(2, level);
            newArr = new int[M][M];
            for (int r = 0; r < M; r += len) {
                for (int c = 0; c < M; c += len) {
                    rotate(r, c, len);
                }
            }
            swap();
            melt();
            swap();
        }

        calculate();
        System.out.println(sum);
        System.out.println(maxSize);
    }

    public static void rotate(int r, int c, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                newArr[r + j][c + len - i - 1] = arr[r + i][c + j];
            }
        }
    }

    public static void melt() {
        newArr = new int[M][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] == 0) continue;
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (0 <= nr && nr < M && 0 <= nc && nc < M && arr[nr][nc] > 0) cnt++;
                }
                if (cnt < 3) newArr[r][c] = arr[r][c] - 1;
                else newArr[r][c] = arr[r][c];
            }
        }
    }

    public static void calculate() {
        boolean[][] visit = new boolean[M][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                if (!visit[r][c] && arr[r][c] > 0) {
                    bfs(visit, r, c);
                }
            }
        }
    }

    public static void bfs(boolean[][] visit, int r, int c) {
        int size = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visit[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            size++;
            sum += arr[cur[0]][cur[1]];
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (0 <= nr && nr < M && 0 <= nc && nc < M && !visit[nr][nc] && arr[nr][nc] > 0) {
                    q.add(new int[]{nr, nc});
                    visit[nr][nc] = true;
                }
            }
        }
        maxSize = Math.max(maxSize, size);
    }

    public static void swap() {
        int[][] tmp = arr;
        arr = newArr;
        newArr = tmp;
    }
}

