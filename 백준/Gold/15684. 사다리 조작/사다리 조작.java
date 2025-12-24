import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선 수
        M = Integer.parseInt(st.nextToken()); // 가로선 수
        H = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][H + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); // 가로 번호
            int v = Integer.parseInt(st.nextToken()); // 세로 번호
            arr[v][h] = v + 1;
            arr[v + 1][h] = v;
        }

        for (int r = 0; r <= 3; r++) {
            comb(0, 0, r);
        }
        System.out.println(-1);
    }

    public static int goDown(int v, int h) {
        if (h == H + 1) return v;

        for (int i = h; i <= H; i++) {
            if (arr[v][i] != 0) {
                return goDown(arr[v][i], i + 1);
            }
        }
        return v;
    }

    public static void comb(int startIdx, int tgtCnt, int r) {
        if (tgtCnt == r) {
            for (int i = 1; i <= N; i++) {
                if (goDown(i, 1) != i) {
                    return;
                }
            }
            System.out.println(r);
            System.exit(0);
        }

        for (int i = startIdx; i < (N - 1) * H; i++) {
            int v = i % (N - 1) + 1;
            int h = i / (N - 1) + 1;

            if (arr[v][h] != 0) continue;
            if (v + 1 <= N && arr[v + 1][h] != 0) continue;

            arr[v][h] = v + 1;
            arr[v + 1][h] = v;

            comb(i + 1, tgtCnt + 1, r);

            arr[v][h] = 0;
            arr[v + 1][h] = 0;
        }
    }
}