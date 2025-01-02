import java.io.*;
import java.util.*;

public class Main {
    public static int M;
    public static int N;
    public static int[][] arr;
    public static Robot start;
    public static Robot end;
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동 서 남 북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        st = new StringTokenizer(br.readLine());
        end = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        bw.write(bfs(start) + "");
        bw.close();
    }

    public static class Robot{
        int r;
        int c;
        int dir;
        int cnt;
        
        public Robot(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    
    public static int bfs(Robot start) {
        Queue<Robot> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[M][N][4];

        queue.add(start);
        visit[start.r][start.c][start.dir] = true;

        while (!queue.isEmpty()) {
            Robot now = queue.poll();

            if (now.r == end.r && now.c == end.c && now.dir == end.dir) {
                return now.cnt;
            }

            // Go 1, Go 2, Go 3
            for (int i = 1; i <= 3; i++) {
                int nr = now.r + delta[now.dir][0] * i;
                int nc = now.c + delta[now.dir][1] * i;

                if (0 > nr || nr >= M || 0 > nc || nc >= N) break;
                
                if (arr[nr][nc] == 0) {
                    if (!visit[nr][nc][now.dir]) {
                        queue.add(new Robot(nr, nc, now.dir, now.cnt + 1));
                        visit[nr][nc][now.dir] = true;
                    }
                } else {
                    break;
                }
            }

            // left, right, left + right
            for (int i = 0; i < 4; i++) {
                if (now.dir != i && !visit[now.r][now.c][i]) {
                    int turn = 1;
                    if ((now.dir == 0 && i == 1) || (now.dir == 1 && i == 0) || (now.dir == 2 && i == 3) || (now.dir == 3 && i == 2)) {
                        turn = 2;
                    }
                    queue.add(new Robot(now.r, now.c, i, now.cnt + turn));
                    visit[now.r][now.c][i] = true;
                }
            }
        }
        return 0;
    }
}