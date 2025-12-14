import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    static int N, M, fare;
    static int[][] arr;
    static Point texi = new Point();
    static int[][] passenger;
    static boolean[] arrive;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] dist; // 택시로부터의 거리
    static int nearestP; // 가장 가까운 손님
    static int minDist; // 가장 가까운 손님과의 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fare = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        passenger = new int[M][4];
        arrive = new boolean[M];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        texi.x = Integer.parseInt(st.nextToken()) - 1;
        texi.y = Integer.parseInt(st.nextToken())- 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            passenger[i][0] = Integer.parseInt(st.nextToken()) - 1;
            passenger[i][1] = Integer.parseInt(st.nextToken()) - 1;
            passenger[i][2] = Integer.parseInt(st.nextToken()) - 1;
            passenger[i][3] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            calDist();
            goToNearestPassenger();
            goToDest();
        }

        boolean flag = true;
        for (int i = 0; i < M; i++) {
            if (!arrive[i]) {
                flag = false;
                break;
            }
        }
        if (flag) System.out.println(fare);
        else System.out.println(-1);
    }

    public static void calDist() {
        Queue<Point> queue = new LinkedList<>();
        dist = new int[N][N];

        queue.add(new Point(texi.x, texi.y));
        dist[texi.x][texi.y] = 1;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.x + delta[i][0];
                int nc = p.y + delta[i][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    queue.add(new Point(nr, nc));
                    dist[nr][nc] = dist[p.x][p.y] + 1;
                }
            }
        }
    }

    public static void goToNearestPassenger() {
        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            if (arrive[i]) continue;

            int r = passenger[i][0];
            int c = passenger[i][1];

            if (dist[r][c] == 0) continue;

            if (minDist > dist[r][c] - 1) {
                minDist = dist[r][c] - 1;
                nearestP = i;
            } else if (minDist == dist[r][c] - 1) {
                if (r < passenger[nearestP][0] || (r == passenger[nearestP][0] && c < passenger[nearestP][1])) {
                    nearestP = i;
                }
            }
        }

        if (minDist == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }

        if (fare >= minDist) {
            fare -= minDist;
            texi.x = passenger[nearestP][0];
            texi.y = passenger[nearestP][1];
        } else {
            System.out.println(-1);
            System.exit(0);
        }

    }

    public static void goToDest() {
        int sr = passenger[nearestP][0];
        int sc = passenger[nearestP][1];
        int er = passenger[nearestP][2];
        int ec = passenger[nearestP][3];

        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[N][N];

        queue.add(new Point(sr, sc));
        dist[sr][sc] = 1;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == er && p.y == ec) {
                if (fare >= dist[er][ec] - 1) {
                    fare += dist[er][ec] - 1;
                    texi.x = er;
                    texi.y = ec;
                    arrive[nearestP] = true;
                } else {
                    System.out.println(-1);
                    System.exit(0);
                }
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.x + delta[i][0];
                int nc = p.y + delta[i][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    queue.add(new Point(nr, nc));
                    dist[nr][nc] = dist[p.x][p.y] + 1;
                }
            }
        }
        System.out.println(-1);
        System.exit(0);
    }
}