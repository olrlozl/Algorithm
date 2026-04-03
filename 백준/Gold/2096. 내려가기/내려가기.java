import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] minDP = new int[N][3];
        int[][] maxDP = new int[N][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            int x = Integer.parseInt(st.nextToken());
            minDP[0][i] = x;
            maxDP[0][i] = x;
        }

        for (int r = 1; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                int x = Integer.parseInt(st.nextToken());
                int minSum = Integer.MAX_VALUE;
                int maxSum = Integer.MIN_VALUE;
                for (int pc = Math.max(0, c - 1); pc <= Math.min(2, c + 1); pc++) {
                    minSum = Math.min(minSum, minDP[r - 1][pc]);
                    maxSum = Math.max(maxSum, maxDP[r - 1][pc]);
                }
                minDP[r][c] = x + minSum;
                maxDP[r][c] = x + maxSum;
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDP[N - 1][i]);
            max = Math.max(max, maxDP[N - 1][i]);
        }
        System.out.println(max + " " + min);
    }
}