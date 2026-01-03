import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] dr = {1, 0 ,-1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][][] dist = new int[N][M][2];

        q.add(new int[] {0, 0, 0});
        dist[0][0][0] = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], broke = cur[2];

            if (r == N - 1 && c == M - 1) {
                return dist[N - 1][M - 1][broke];
            }

            for (int j = 0; j < 4; j++) {
                int nr = r + dr[j];
                int nc = c + dc[j];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (arr[nr][nc] == 0 && dist[nr][nc][broke] == 0) {
                    q.add(new int[] {nr, nc, broke});
                    dist[nr][nc][broke] = dist[r][c][broke] + 1;
                }

                if (arr[nr][nc] == 1 && broke == 0 && dist[nr][nc][1] == 0) {
                    q.add(new int[] {nr, nc, 1});
                    dist[nr][nc][1] = dist[r][c][0] + 1;
                }
            }
        }
        return -1;
    }
}