import java.io.*;

public class Solution {
    public static int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}}; // 하 우 좌 상

    public static void dfs (int[][] arr, boolean[][] visit, int r, int c) {
        visit[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];
            if ((arr[nr][nc] == 0 || arr[nr][nc] == 3) && !visit[nr][nc]) {
                dfs(arr, visit, nr, nc);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 1; t <= 10; t++) {
            br.readLine();
            int[][] arr = new int[16][16];

            int x = 0; // 도착지점 x좌표
            int y = 0; // 도착지점 y좌표

            for (int i = 0; i < 16; i++) {
                char[] charr = br.readLine().toCharArray();
                for (int j = 0; j < 16; j++) {
                    arr[i][j] = charr[j] - '0';
                    if (arr[i][j] == 3) {
                        x = i;
                        y = j;
                    }
                }
            }

            boolean[][] visit = new boolean[16][16];
            dfs(arr, visit, 1, 1);

            if (visit[x][y] == true) bw.write("#" + t + " 1\n");
            else bw.write("#" + t + " 0\n");
        }
        bw.close();
    }
}
