import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static boolean[][] origin, arr; // 적
    static boolean[] archer; // 궁수
    static HashSet<String> target; // 타겟 적
    static int[] dr = {-1, 0, 0};
    static int[] dc = {0, -1, 1};
    static int cnt;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][M];
        origin = new boolean[N + 1][M];
        archer = new boolean[M];
        target = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    arr[i][j] = true;
                    origin[i][j] = true;
                }
            }
        }
        comb(0, 0);
        System.out.println(result);
    }

    public static void comb(int startIdx, int tgtCnt) {
        if (tgtCnt == 3) {
            cnt = 0;
            play();
            restore();
            result = Math.max(result, cnt);
            return;
        }
        for (int i = startIdx; i < M; i++) {
            if (!archer[i]) {
                archer[i] = true;
                comb(i + 1, tgtCnt + 1);
                archer[i] = false;
            }
        }
    }

    public static void play() {
        for (int r = N; r > 0 ; r--) {
            for (int c = 0; c < M; c++) {
                if (archer[c]) {
                    bfs(r, c);
                }
            }
            for (String tgt : target) {
                int tgtR = Integer.parseInt(tgt.split(" ")[0]);
                int tgtC = Integer.parseInt(tgt.split(" ")[1]);
                arr[tgtR][tgtC] = false;
                cnt++;
            }
            target.clear();
        }
    }

    public static void bfs(int r, int c) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N + 1][M];

        q.add(new int[] {r, c});
        visit[r][c] = true;

        int tgtR = Integer.MAX_VALUE;
        int tgtC = Integer.MAX_VALUE;
        int d = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                if (d > 0 && cur[0] != r && arr[cur[0]][cur[1]] && cur[1] < tgtC) {
                    tgtR = cur[0];
                    tgtC = cur[1];
                }

                if (tgtR != Integer.MAX_VALUE && tgtC != Integer.MAX_VALUE) continue;

                for (int j = 0; j < 3; j++) {
                    int nr = cur[0] + dr[j];
                    int nc = cur[1] + dc[j];
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && !visit[nr][nc]) {
                        q.add(new int[] {nr, nc});
                        visit[nr][nc] = true;
                    }
                }
            }
            if (tgtR != Integer.MAX_VALUE && tgtC != Integer.MAX_VALUE) {
                target.add(tgtR + " " + tgtC);
                return;
            }
            if (d++ == D) return;
        }
    }

    public static void restore() {
        for (int i = 0; i <= N; i++) {
            System.arraycopy(origin[i], 0, arr[i], 0, M);
        }
    }
}

