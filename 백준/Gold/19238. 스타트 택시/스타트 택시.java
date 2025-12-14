import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long fuel;
    static int[][] arr;
    static int texiR;
    static int texiC;
    static int[][] passenger; // 출발지, 도착지
    static int[][] pidMap; // 승객 id, 없으면 -1
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
        pidMap = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(pidMap[i], -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        texiR = Integer.parseInt(st.nextToken()) - 1;
        texiC = Integer.parseInt(st.nextToken())- 1;

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

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[N][N];

        queue.add(new int[]{texiR, texiC});
        dist[texiR][texiC] = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int pid = pidMap[r][c];
            int d = dist[r][c] - 1;

            if (d > minDist) break; // 더 먼 거리의 승객은 볼 필요 없음

            if (pid != -1) {
                if (minDist == Integer.MAX_VALUE) minDist = d;
                if (r < bestR || (r == bestR && c < bestC)) {
                    bestR = r;
                    bestC = c;
                    nearestP = pid;
                }
            }

            for (int j = 0; j < 4; j++) {
                int nr = r + delta[j][0];
                int nc = c + delta[j][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc});
                    dist[nr][nc] = dist[r][c] + 1;
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
            texiR = sr;
            texiC = sc;
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

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[N][N];

        queue.add(new int[]{sr, sc});
        dist[sr][sc] = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            if (r == er && c == ec) {
                if (fuel >= dist[er][ec] - 1) {
                    fuel += dist[er][ec] - 1;
                    texiR = er;
                    texiC = ec;
                } else {
                    System.out.println(-1);
                    System.exit(0);
                }
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc});
                    dist[nr][nc] = dist[r][c] + 1;
                }
            }
        }
        System.out.println(-1);
        System.exit(0);
    }
}