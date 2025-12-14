import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    static int N, M;
    static long fuel;
    static int[][] arr;
    static Point texi = new Point();
    static int[][] passenger; // 출발지, 도착지
    static int[][] pidMap; // 승객 id, 없으면 -1
    static boolean[] arrive; // 승객 도착 여부
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int nearestP; // 가장 가까운 손님
    static int minDist; // 가장 가까운 손님과의 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        passenger = new int[M][4];
        arrive = new boolean[M];
        pidMap = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(pidMap[i], -1);

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
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            passenger[i][2] = Integer.parseInt(st.nextToken()) - 1;
            passenger[i][3] = Integer.parseInt(st.nextToken()) - 1;

            pidMap[sr][sc] = i;
            passenger[i][0] = sr;
            passenger[i][1] = sc;
        }

        for (int i = 0; i < M; i++) {
            findPassenger();
            goToPassenger();
            goToDest();
        }

        System.out.println(fuel);
    }

    public static void findPassenger() {
        nearestP = -1;
        minDist = Integer.MAX_VALUE;
        int bestR = Integer.MAX_VALUE;
        int bestC = Integer.MAX_VALUE;

        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[N][N];

        queue.add(new Point(texi.x, texi.y));
        dist[texi.x][texi.y] = 1;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            int pid = pidMap[p.x][p.y];
            int d = dist[p.x][p.y] - 1;

            if (d > minDist) break; // 더 먼 거리의 승객은 볼 필요 없음

            if (pid != -1 && !arrive[pid]) {
                if (minDist == Integer.MAX_VALUE) minDist = d;
                if (p.x < bestR || (p.x == bestR && p.y < bestC)) {
                    bestR = p.x;
                    bestC = p.y;
                    nearestP = pid;
                }
            }

            for (int j = 0; j < 4; j++) {
                int nr = p.x + delta[j][0];
                int nc = p.y + delta[j][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    queue.add(new Point(nr, nc));
                    dist[nr][nc] = dist[p.x][p.y] + 1;
                }
            }
        }
    }

    public static void goToPassenger() {
        if (minDist == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }

        if (fuel >= minDist) {
            fuel -= minDist;
            int sr = passenger[nearestP][0];
            int sc = passenger[nearestP][1];
            texi.x = sr;
            texi.y = sc;
            pidMap[sr][sc] = -1;
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
                if (fuel >= dist[er][ec] - 1) {
                    fuel += dist[er][ec] - 1;
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