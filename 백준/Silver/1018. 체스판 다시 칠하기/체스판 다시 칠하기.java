import java.io.*;
import java.util.*;

public class Main {

    public static int Check(char[][] arr, int r, int c) {
        int cnt = 0;
        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c + 8; j++) {
                if ((i + j) % 2 == 0 && arr[i][j] == 'W') cnt++;
                else if ((i + j) % 2 == 1 && arr[i][j] == 'B') cnt++;
            }
        }
        return Math.min(cnt, 64 - cnt);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] arr = new char[m][n];
        for (int r = 0; r < m; r++) arr[r] = br.readLine().toCharArray();

        int minCnt = Integer.MAX_VALUE;

        for (int r = 0; r <= m - 8; r++) {
            for (int c = 0; c <= n - 8; c++) {
                minCnt = Math.min(minCnt, Check(arr, r, c));
            }
        }

        bw.write(minCnt + "");
        bw.close();
    }
}