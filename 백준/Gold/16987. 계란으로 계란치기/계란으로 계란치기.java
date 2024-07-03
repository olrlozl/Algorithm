import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] eggs;
    public static int maxCnt = 0;

    public static void simulation(int now, int cnt) {
        if (now == N || cnt >= N - 1) { // 바로 이전에 든 계란이 마지막 계란이였거나, 모든 계란이 깨졌거나, 한 개만 안깨진 경우
            maxCnt = Math.max(maxCnt, cnt);
            return; // 종료
        }

        if (eggs[now][0] <= 0) { // 손에 든 계란이 깨졌다면
            simulation(now + 1, cnt); // 치지않고 넘어가서 다음 계란을 든다
        } else {
            for (int i = 0; i < N; i++) { // 모든 다른 계란들에 대해
                if (now == i || eggs[i][0] <= 0) { // 자기자신이거나, 다른 계란이 이미 깨졌다면 넘어간다
                    continue;
                }
                eggs[now][0] -= eggs[i][1]; // 현재 계란 내구도 감소
                eggs[i][0] -= eggs[now][1]; // 다른 계란 내구도 감소

                simulation(now + 1, cnt + (eggs[now][0] <= 0 ? 1: 0) + (eggs[i][0] <= 0 ? 1 : 0)); // 다음 계란을 든다

                // for문의 다음 i를 위해 현재 i에 대한 결과를 복원
                eggs[now][0] += eggs[i][1]; // 현재 계란 내구도 복원
                eggs[i][0] += eggs[now][1]; // 다른 계란 내구도 복원
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 계란의 수
        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken()); // 계란의 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken()); // 계란의 무게
        }

        simulation(0, 0);
        bw.write(maxCnt + "");
        bw.close();
    }
}