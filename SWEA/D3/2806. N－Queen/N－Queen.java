import java.io.*;
import java.util.*;

public class Solution {

    public static int result; // 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수
    public static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}}; // 북서, 북, 북동

    public static void N_Queen (int[][] chess, int r, int N) {
        // 기저조건
        if (r == N) {
            result++;
            return;
        }

        // r행 에서 퀸이 들어갈 수 있는 자리 찾기
        loop: for (int c = 0; c < N; c++) {
            for (int d = 0; d < 3; d++) { // 북서, 북, 북동 쪽에 대하여 퀸이 있는지 확인
                for (int k = 1; k < N; k++) { // 한 칸씩 넓혀가며 확인
                    int nr = r + delta[d][0] * k;
                    int nc = c + delta[d][1] * k;
                    if (nr < 0 || N <= nr || nc < 0 || N <= nc) break; // 체스판 범위를 벗어난다면, 다음 방향 확인하러 넘어가기
                    if (chess[nr][nc] == 1) continue loop; // 같은 행, 열, 대각선 중에 퀸이 있다면, 다음 c열 확인하러 넘어가기
                }
            }
            // 여기까지 왔다는 것은 같은 행,열,대각선에 퀸이 없었기에 r행 c열에 퀸을 배정할 수 있다는 뜻
            chess[r][c] = 1; // r행 c열에 퀸 배정
            N_Queen(chess, r + 1, N); // 다음(r+1)행에서 퀸이 들어갈 수 있는 자리 찾기 위해 재귀 호출
            chess[r][c] = 0; // r행 c열에 퀸을 배정하지 않는 경우도 보기 위해 0으로 재설정하고 다음 c열 확인하기
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] chess = new int[N][N];

            result = 0; // 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수
            N_Queen(chess, 0, N); // 0행부터 시작해서 퀸 N개를 배정하고 서로 공격할 수 없는 경우의 수 카운트

            bw.write("#" + t + " " + result + "\n");
        }
        bw.close();
    }
}