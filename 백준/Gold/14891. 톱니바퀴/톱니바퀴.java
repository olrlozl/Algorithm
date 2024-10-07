import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] cogs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        cogs = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            cogs[i] = new ArrayList<>();
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                cogs[i].add(line[j] - '0');
            }
        }


        int K = Integer.parseInt(br.readLine()); // 회전횟수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1; // 번호
            int dir = Integer.parseInt(st.nextToken()); // 방향 (1시계, -1반시계)

            int[] rotate = new int[4]; // 0회전안함 1시계 2반시계
            rotate[idx] = dir;

            // 왼쪽 회전상태 확인
            int cur = idx;
            for (int left = idx - 1; left >= 0; left--) {
                if (cogs[cur].get(6) == cogs[left].get(2)) break;
                rotate[left] = -rotate[cur];
                cur--;
            }

            // 오른쪽 회전상태 확인
            cur = idx;
            for (int right = idx + 1; right < 4; right++) {
                if (cogs[cur].get(2) == cogs[right].get(6)) break;
                rotate[right] = -rotate[cur];
                cur++;
            }

            // 회전
            for (int j = 0; j < 4; j++) {
                if (rotate[j] == 1) cogs[j].add(0, cogs[j].remove(7));
                else if (rotate[j] == -1) cogs[j].add(7, cogs[j].remove(0));
            }
        }

        bw.write((cogs[0].get(0) + cogs[1].get(0) * 2 + cogs[2].get(0) * 4 + cogs[3].get(0) * 8) + "");
        bw.close();
    }
}