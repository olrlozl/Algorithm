import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    public static int N; // 가로
    public static int M; // 세로

    public static int bfs (int[][] box) {
        int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
        Queue<Point> queue = new LinkedList<>();

        // 익은 토마토를 모두 큐에 넣기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (box[r][c] == 1) {
                    queue.add(new Point(r,c));
                }
            }
        }

        while (!queue.isEmpty()) { // 큐가 빌 때 까지
            Point tomato = queue.poll(); // 큐에서 토마토 하나를 꺼내고
            // 상하좌우를 확인해보면서
            for (int d = 0; d < 4; d++) {
                int nr = tomato.x + delta[d][0];
                int nc = tomato.y + delta[d][1];
                // 이웃 토마토 중에 익지 않은 토마토가 있다면
                if (0 <= nr && nr < N && 0 <= nc && nc < M && box[nr][nc] == 0) {
                    queue.add(new Point(nr, nc)); // 큐에 넣고
                    box[nr][nc] = box[tomato.x][tomato.y] + 1; // 방문 처리
                }
            } // for
        } // while

        int max = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (box[r][c] == 0) return -1; // 익지 않은 토마토가 있다면 -1
                if (box[r][c] > max) max = box[r][c];
            }
        }
        return max - 1; // 토마토가 모두 익을 때까지 최소 날짜
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());// 가로 칸의 수
        N = Integer.parseInt(st.nextToken());// 세로 칸의 수

        int[][] box = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                box[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(bfs(box) + "");
        bw.close();
    }
}
