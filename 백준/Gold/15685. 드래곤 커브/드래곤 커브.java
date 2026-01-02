import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean[][] arr = new boolean[101][101];

        int[] dr = {0, -1, 0, 1};
        int[] dc = {1, 0, -1, 0};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            ArrayList<Point> list = new ArrayList<>();
            Point end;

            list.add(new Point(r, c));
            list.add(new Point(r + dr[d], c + dc[d]));

            arr[r][c] = true;
            arr[r + dr[d]][c + dc[d]] = true;

            for (int j = 0; j < g; j++) {
                int endIdx = list.size() - 1;
                end = list.get(endIdx);

                for (int k = endIdx - 1; k >= 0; k--) {
                    Point p = list.get(k);
                    int a = p.x - end.x;
                    int b = p.y - end.y;
                    int nr = end.x + b;
                    int nc = end.y - a;

                    list.add(new Point(nr, nc));
                    arr[nr][nc] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] && arr[i + 1][j] && arr[i][j + 1] && arr[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}