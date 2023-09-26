import javax.imageio.ImageReader;
import java.io.*;
import java.util.*;

public class Solution {

    public static void Clock (int[][] arr, int m) {
        int tmp = arr[m][7];
        for (int j = 6; j >= 0; j--) arr[m][j + 1] = arr[m][j];
        arr[m][0] = tmp;
    }

    public static void reverseClock (int[][] arr, int m) {
        int tmp = arr[m][0];
        for (int j = 1; j <= 7; j++) arr[m][j - 1] = arr[m][j];
        arr[m][7] = tmp;
    }

    public static void rotate(int[][] arr, int[] turn) {
        for (int i = 0; i < 4; i++) {
            if (turn[i] == 1) Clock(arr, i);
            else if (turn[i] == -1) reverseClock(arr, i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());

            int[][] arr = new int[4][8];

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int magnet = Integer.parseInt(st.nextToken()) - 1;
                int direct = Integer.parseInt(st.nextToken());

                // 회전 정보 저장 (시계방향 1, 반시계방향 -1, 유지 0)
                int[] turn = new int[4];

                turn[magnet] = direct;

                // 왼쪽 체크
                for (int m = magnet; m >= 1; m--) {
                    if (arr[m - 1][2] == arr[m][6]) break;
                    if (m % 2 == magnet % 2) turn[m - 1] = - direct;
                    else turn[m - 1] = direct;
                }

                // 오른쪽 체크
                for (int m = magnet; m <= 2; m++) {
                    if (arr[m][2] == arr[m + 1][6]) break;
                    if (m % 2 == magnet % 2) turn[m + 1] = - direct;
                    else turn[m + 1] = direct;
                }

                rotate(arr, turn);
            }

            bw.write("#" + t + " " + (arr[0][0] + arr[1][0] * 2 + arr[2][0] * 4 + arr[3][0] * 8) + "\n");
        }
        bw.close();
    }
}