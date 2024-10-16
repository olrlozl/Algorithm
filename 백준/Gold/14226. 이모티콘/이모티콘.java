import java.io.*;
import java.util.*;

public class Main {
    public static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = Integer.parseInt(br.readLine());

        bw.write(bfs() + "");
        bw.close();
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>(); // [화면, 클립보드, 시간]
        boolean[][] visit = new boolean[1001][1001]; // 화면, 클립보드

        queue.add(new int[] {1, 0, 0});
        visit[1][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == S) return now[2];
            // 복사
            if (!visit[now[0]][now[0]]) {
                queue.add(new int[] {now[0], now[0], now[2] + 1});
                visit[now[0]][now[2]] = true;
            }
            // 붙여넣기
            if (now[1] > 0 && now[0] + now[1] <= 1000 && !visit[now[0] + now[1]][now[1]]) {
                queue.add(new int[]{now[0] + now[1], now[1], now[2] + 1});
                visit[now[0] + now[1]][now[1]] = true;
            }
            // 삭제
            if (now[0] > 0 && !visit[now[0] - 1][now[1]]) {
                queue.add(new int[]{now[0] - 1, now[1], now[2] + 1});
                visit[now[0] - 1][now[1]] = true;
            }
        }
        return 0;
    }
}