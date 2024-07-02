import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int N;
    public static int M;
    public static int T;
    public static int[][] arr;
    public static int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    public static int time = 0;

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        queue.add(new Point(0, 0));
        visit[0][0] = true;
        int cnt = 0; // 그람 사용 X
        int gram = 100000; // 그람 사용 O

        loop: while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Point cur = queue.poll();
                if (cur.x == N - 1 && cur.y == M - 1) {
                    break loop;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = cur.x + delta[d][0];
                    int nc = cur.y + delta[d][1];
                    if (0 > nr || nr >= N || 0 > nc || nc >= M || visit[nr][nc]) continue;
                    if (arr[nr][nc] == 0) {
                        queue.add(new Point(nr,nc));
                        visit[nr][nc] = true;
                    } else if (arr[nr][nc] == 2) {
                        visit[nr][nc] = true;
                        gram = cnt + 1 + N - 1 - nr + M - 1 - nc;
                    }
                }
            }
            cnt++;
        }
        if (visit[N - 1][M - 1]) { // 그람 없이 공주님을 구할 수 있다면
            time = Math.min(cnt, gram); // 그람을 사용 했을 때와 사용 하지 않았을 때 중 더 짧은 것을 선택
        } else { // 그람 없이 공주님을 구할수 없다면
            time = gram; // 그람에 도달할 수 있다면 그람을 사용해서 공주님을 구하자 (그람을 못 만나서 gram == 0 인 경우도 있음)
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        T = Integer.parseInt(st.nextToken()); // 제한시간

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        if (0 < time && time <= T) bw.write(time + "");
        else bw.write("Fail");
        bw.close();
    }
}