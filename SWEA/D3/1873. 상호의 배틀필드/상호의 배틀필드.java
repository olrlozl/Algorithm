import java.io.*;
import java.util.*;

public class Solution {
    public static char[][] arr; // 게임 맵 상태
    public static int h; // 게임 맵 높이
    public static int w; // 게임 맵 너비
    public static int r = 0; // 전차 행 좌표
    public static int c = 0; // 전차 열 좌표
    public static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    public static char[] head = {'^', 'v', '<', '>'}; // 상 하 좌 우
    public static int now;


    public static void turn(int d) {
        arr[r][c] = head[d];
        now = d;
        tryMove(d);
    }

    public static void shoot() {
        int nr = r;
        int nc = c;

        while (true) {
            nr += delta[now][0];
            nc += delta[now][1];

            // 다음 칸이 게임 맵 범위를 벗어난다면 끝내기
            if (nr == -1 || nr == h || nc == -1 || nc == w) break;

            // 다음 칸이 게임 맵 범위를 벗어나지 않는다면
            else {
                if (arr[nr][nc] == '*') { // 벽돌 벽을 만나면
                    arr[nr][nc] = '.'; // 부수고 평지로 만들고
                    break; // 포탄 소멸
                } else if (arr[nr][nc] == '#') { // 강철 벽을 만나면
                    break; // 포탄 소멸
                }
            }
        }
    }

    public static void tryMove(int d) {
        int nr = r + delta[d][0];
        int nc = c + delta[d][1];
        // 다음 칸이 범위를 벗어나지 않고 평지라면
        if (0 <= nr && nr < h && 0 <= nc && nc < w && arr[nr][nc] == '.') {
            arr[r][c] = '.'; // 전차 있던 위치는 평지로 바꾸고
            arr[nr][nc] = head[d]; // 다음 칸에 전차 배치
            r = nr; // 전차의 행 좌표 이동
            c = nc; // 전차의 열 좌표 이동
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()); // 게임 맵 높이
            w = Integer.parseInt(st.nextToken()); // 게임 맵 너비

            arr = new char[h][w]; // 게임 맵 상태
            for (int i = 0; i < h; i++) {
                char[] charr = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    arr[i][j] = charr[j];
                    if (arr[i][j] == '>' || arr[i][j] == '<' || arr[i][j] == '^' || arr[i][j] == 'v') {
                        r = i; // 전차 행 좌표
                        c = j; // 전차 열 좌표
                        if (arr[i][j] == '^') now = 0; // 상
                        else if (arr[i][j] == 'v') now = 1; // 하
                        else if (arr[i][j] == '<') now = 2; // 좌
                        else if (arr[i][j] == '>') now = 3; // 우
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());
            char[] order = br.readLine().toCharArray();

            for (int i = 0; i < n; i++) {
                switch (order[i]) {
                    case 'U':
                        turn(0);
                        break;
                    case 'D':
                        turn(1);
                        break;
                    case 'L':
                        turn(2);
                        break;
                    case 'R':
                        turn(3);
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }

            bw.write("#" + t + " ");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    bw.write(arr[i][j]);
                }
                bw.newLine();
            }
        }
        bw.close();
    }
}