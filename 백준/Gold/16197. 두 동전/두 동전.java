import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static char[][] arr;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] coin = new int[5]; // [동전1행, 동전1열, 동전2행, 동전2열, 이동횟수]
        arr = new char[N][M];

        for (int i = 0, idx = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'o') {
                    coin[idx++] = i;
                    coin[idx++] = j;
                }
            }
        }

        bw.write(bfs(coin) + "");
        bw.close();
    }

    public static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>(); // [동전1행, 동전1열, 동전2행, 동전2열, 이동횟수]
        boolean[][][][] visit = new boolean[N][M][N][M];

        queue.add(start);
        visit[start[0]][start[1]][start[2]][start[3]] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[4] >= 10) break; // 10번 보다 더 많이 이동 불가능

            for (int i = 0; i < 4; i++) {
                int nr1 = now[0] + delta[i][0];
                int nc1 = now[1] + delta[i][1];
                int nr2 = now[2] + delta[i][0];
                int nc2 = now[3] + delta[i][1];

                int out = 0; // 떨어지는 동전 수

                // 첫 번째 동전이
                if (nr1 < 0 || N <= nr1 || nc1 < 0 || M <= nc1) { // 떨어진다면
                    out++;
                } else if (arr[nr1][nc1] == '#') { // 벽이라면
                    nr1 = now[0]; // 이동안함
                    nc1 = now[1]; // 이동안함
                }

                // 두 번째 동전이
                if (nr2 < 0 || N <= nr2 || nc2 < 0 || M <= nc2) { // 떨어진다면
                    out++;
                } else if (arr[nr2][nc2] == '#') { // 벽이라면
                    nr2 = now[2]; // 이동안함
                    nc2 = now[3]; // 이동안함
                }

                if (out == 1) { // 한 개만 떨어졌다면
                    return now[4] + 1;
                } else if (out == 0 && !visit[nr1][nc1][nr2][nc2]) { // 두 개 다 남았고 이렇게 배치된 적이 없었다면
                    queue.add(new int[] {nr1, nc1, nr2, nc2, now[4] + 1});
                    visit[nr1][nc1][nr2][nc2] = true;
                }
            }
        }
        return -1;
    }
}