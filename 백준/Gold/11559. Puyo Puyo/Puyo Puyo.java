import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new char[12][6];
        for (int i = 0; i < 12; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int cnt = 0;

        while (round()) {
            cnt++;
            down();
        }

        bw.write(cnt + "");
        bw.close();
    }

    public static boolean round() {
        boolean[][] visit = new boolean[12][6];
        boolean pang = false;

        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 6; c++) {
                if (arr[r][c] != '.' && !visit[r][c]) {
                    if (bfs(visit, new Point(r, c))) {
                        pang = true;
                    }
                }
            }
        }
        return pang;
    }

    public static boolean bfs(boolean[][] visit, Point start) {
        int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        char color = arr[start.x][start.y];
        int puyo = 0;

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();

        queue.add(start);
        list.add(start);
        visit[start.x][start.y] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            puyo++;

            for (int d = 0; d < 4; d++) {
                int nr = now.x + delta[d][0];
                int nc = now.y + delta[d][1];

                if (0 <= nr && nr < 12 && 0 <= nc && nc < 6
                        && !visit[nr][nc] && color == arr[nr][nc]) {
                    queue.add(new Point(nr, nc));
                    list.add(new Point(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }

        if (puyo >= 4) {
            for (int i = 0; i < list.size(); i++) {
                arr[list.get(i).x][list.get(i).y] = '.';
            }
            return true;
        }

        return false;
    }

    public static void down() {
        for (int c = 0; c < 6; c++) {
            char[] colum = new char[12];
            Arrays.fill(colum, '.');
            int idx = 11;

            for (int r = 11; r >= 0; r--) {
                if (arr[r][c] != '.') {
                    colum[idx--] = arr[r][c];
                }
            }
            for (int i = 0; i < 12; i++) {
                arr[i][c] = colum[i];
            }
        }
    }
}