import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int r;
    public static int c;
    public static int d;
    public static int[][] room;
    public static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
    public static int cnt;

    public static void tryClean() {
        if (room[r][c] == 0) {
            cnt++;
            room[r][c] = 2; // 청소 완료
        }
    }
    public static void back() {
        if (d == 0) r++;
        else if (d == 1) c--;
        else if (d == 2) r--;
        else if (d == 3) c++;
    }

    public static boolean isDirty() {
        boolean state = false;

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (room[nr][nc] != 0) continue;
            state = true;
        }

        return state;
    }

    public static void rotateNforward() {
        for (int i = 0; i < 4; i++) {
            d = (d == 0 ? 3 : d - 1); // 반시계 90도 회전
            if (room[r + delta[d][0]][c + delta[d][1]] == 0) { // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우, 한 칸 전진
                r += delta[d][0];
                c += delta[d][1];
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로


        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 로봇 청소기 행 위치
        c = Integer.parseInt(st.nextToken()); // 로봇 청소기 열 위치
        d = Integer.parseInt(st.nextToken()); // 북0 동1 남2 서3

        room = new int[n][m]; // 방 상태

        for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            tryClean();
            if (isDirty()) rotateNforward();
            else {
                back();
                if (room[r][c] == 1) break;
            }
        }

        bw.write(cnt + "");
        bw.close();

    }
}