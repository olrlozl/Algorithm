import java.io.*;
import java.util.*;

public class Main {
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int N;
    public static int M;
    public static int[][] arr;
    public static boolean[][] visit;
    public static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, 0, arr[i][j]);
                visit[i][j] = false;
            }
        }

        bw.write(maxSum + "");
        bw.close();
    }

    public static void dfs(int r, int c, int depth, int sum) {
        if (depth == 3) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visit[nr][nc]) {
                if (depth == 1) {
                    visit[nr][nc] = true;
                    dfs(r, c, depth + 1, sum + arr[nr][nc]);
                    visit[nr][nc] = false;
                }
                visit[nr][nc] = true;
                dfs(nr, nc, depth + 1, sum + arr[nr][nc]);
                visit[nr][nc] = false;
            }
        }
    }
}