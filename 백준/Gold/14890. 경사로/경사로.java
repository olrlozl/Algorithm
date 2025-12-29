import java.io.*;
import java.util.*;

public class Main {
    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        int[] row = new int[N];
        int[] col = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                row[j] = map[i][j];
                col[j] = map[j][i];
            }
            if (isPass(row)) result++;
            if (isPass(col)) result++;
        }

        System.out.println(result);
    }

    public static boolean isPass(int[] arr) {
        int idx = 1;
        boolean[] slope = new boolean[N];

        while (idx < N) {
            int diff = arr[idx] - arr[idx - 1];

            if (diff == 0) {
                idx++;
            } else if (diff == 1) {
                for (int d = 1; d <= L; d++) {
                    if (idx - d < 0 || arr[idx] - arr[idx - d] != 1 || slope[idx - d]) {
                        return false;
                    }
                }
                for (int d = 1; d <= L; d++) {
                    slope[idx - d] = true;
                }
                idx++;
            } else if (diff == -1) {
                for (int d = 1; d < L; d++) {
                    if (idx + d >= N || arr[idx] != arr[idx + d]) {
                        return false;
                    }
                }
                for (int d = 0; d < L; d++) {
                    slope[idx + d] = true;
                }
                idx += L;
            } else {
                return false;
            }
        }
        return true;
    }
}