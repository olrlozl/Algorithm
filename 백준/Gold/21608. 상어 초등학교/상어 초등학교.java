import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] order;
    public static int[][] seat;
    public static ArrayList<Integer>[] favorite;
    public static int[][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        order = new int[N * N];
        seat = new int[N][N];

        favorite = new ArrayList[N * N + 1];
        for (int i = 0; i <= N * N; i++) {
            favorite[i] = new ArrayList<>();
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            order[i] = num;
            for (int j = 0; j < 4; j++) {
                favorite[num].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N * N; i++) {
            int num = order[i];
            ArrayList<int[]> list = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    calPriority(list, num, r, c);
                }
            }
            Collections.sort(list, (a, b) -> {
                if (a[0] != b[0]) return b[0] - a[0];
                if (a[1] != b[1]) return b[1] - a[1];
                if (a[2] != b[2]) return a[2] - b[2];
                return a[3] - b[3];
            });
            seat[list.get(0)[2]][list.get(0)[3]] = num;
        }

        bw.write(calSatisfaction() + "");
        bw.close();
    }

    public static void calPriority(ArrayList<int[]> list, int num, int r, int c) {
        if (seat[r][c] != 0) return;
        int cntNearFavorite = 0;
        int cntNearEmpty = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            // 인접한 칸에 좋아하는 학생이 있다면
            if (favorite[num].contains(seat[nr][nc])) cntNearFavorite++;
            // 인접한 칸이 비어있다면
            else if (seat[nr][nc] == 0) cntNearEmpty++;
        }
        list.add(new int[] {cntNearFavorite, cntNearEmpty, r, c});
    }

    public static int calSatisfaction() {
        int satisfaction = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int cnt = 0;
                int num = seat[r][c];
                for (int i = 0; i < 4; i++) {
                    int nr = r + delta[i][0];
                    int nc = c + delta[i][1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (favorite[num].contains(seat[nr][nc])) cnt++;
                }
                if (cnt > 0) satisfaction += Math.pow(10, cnt - 1);
            }
        }
        return satisfaction;
    }
}