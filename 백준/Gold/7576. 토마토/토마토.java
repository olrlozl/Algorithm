import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int M; // 가로
    public static int N; // 세로
    public static int[][] arr; // 상자 속 토마토 정보
    public static ArrayList<Point> ripe = new ArrayList<>();; // 익은 토마토
    public static int unripe = 0; // 익지 않은 토마토 개수
    public static int day = 0; // 모든 토마토가 익기 위한 최소 일수
    public static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) ripe.add(new Point(i, j));
                else if (arr[i][j] == 0) unripe++;
            }
        }

        if (unripe > 0) bfs();
        bw.write((unripe == 0 ? day : -1) + "");
        bw.close();
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>(ripe);
        
        while (!queue.isEmpty()) {
            int len = queue.size();
            boolean flag = false;
            for (int i = 0; i < len; i++) {
                Point now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.x + delta[d][0];
                    int nc = now.y + delta[d][1];
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0) {
                        queue.add(new Point(nr, nc));
                        arr[nr][nc] = 1;
                        unripe--;
                        flag = true;
                    }
                }
            }
            if (flag) day++;
        }
    }
}