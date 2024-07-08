import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 세로
        int N = Integer.parseInt(st.nextToken()); // 가로

        int[][] arr = new int[M][N];
        int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
        arr[0][0] = 1;
        int r = 0;
        int c = 0;
        int d = 0;
        int cnt = 1;

        while (true) {
            int nr = r + delta[d % 4][0];
            int nc = c + delta[d % 4][1];
            if (0 <= nr && nr < M && 0 <= nc && nc < N && arr[nr][nc] == 0) {
                arr[nr][nc] = 1;
                cnt++;
                r = nr;
                c = nc;
            } else {
                d++;
            }
            if (cnt == M * N) break;
        }

        bw.write(d + " ");
        bw.close();
    }
}