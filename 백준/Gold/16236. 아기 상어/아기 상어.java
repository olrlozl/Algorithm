import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int N;
    public static int[][] arr;
    public static int[][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 상 좌 우 하
    public static int sharkSize = 2; // 현재 아기 상어 크기
    public static int eatCnt; // 현재 크기일 때 먹은 물고기의 수
    public static int result;

    public static void bfs(int r, int c) {
        Queue<Point> CanPassQ = new LinkedList<>();
        int[][] visit = new int[N][N];

        CanPassQ.add(new Point(r, c));
        visit[r][c] = 1;

        PriorityQueue<Point> CanEatPQ = new PriorityQueue<>((p1, p2) -> {
            if (p1.x == p2.x) return p1.y - p2.y;
            else return p1.x - p2.x;
        });
        boolean isEat = false;

        while(!CanPassQ.isEmpty()) {
            int len = CanPassQ.size();

            for (int i = 0; i < len; i++) {
                Point cur = CanPassQ.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.x + delta[d][0];
                    int nc = cur.y + delta[d][1];

                    if (0 <= nr && nr < N && 0 <= nc && nc < N 
                            && visit[nr][nc] == 0 && arr[nr][nc] <= sharkSize) {
                        CanPassQ.add(new Point(nr, nc));
                        visit[nr][nc] = visit[cur.x][cur.y] + 1;

                        if (0 < arr[nr][nc] && arr[nr][nc] < sharkSize) {
                            CanEatPQ.add(new Point(nr, nc));
                        }
                    }
                }
            }
            if (!CanEatPQ.isEmpty()) {
                Point food = CanEatPQ.poll();
                result += visit[food.x][food.y] - 1;
                arr[r][c] = 0;
                arr[food.x][food.y] = 9;
                r = food.x;
                c = food.y;
                if (sharkSize == ++eatCnt) {
                    sharkSize++;
                    eatCnt = 0;
                }
                isEat = true;
                break;
            }
        }

        if (isEat) bfs(r, c);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        int r = -1; // 아기상어 행 위치
        int c = -1; // 아기상어 열 위치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    r = i;
                    c = j;
                }
            }
        }

        bfs(r, c);

        bw.write(result + "");
        bw.close();
    }
}