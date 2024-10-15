import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌 (앞으로 이동)

        for (int i = 0; i < T; i++) {
            char[] order = br.readLine().toCharArray();
            int minR = 0, maxR = 0, minC = 0, maxC = 0;
            int r = 0, c = 0;
            int dir = 0;

            for (char o: order) {
                if (o == 'F') {
                    r += delta[dir][0];
                    c += delta[dir][1];
                } else if (o == 'B') {
                    r -= delta[dir][0];
                    c -= delta[dir][1];
                } else if (o == 'L') {
                    dir = (dir == 0 ? 3 : dir - 1);
                } else if (o == 'R') {
                    dir = (dir + 1) % 4;
                }
                minR = Math.min(minR, r);
                maxR = Math.max(maxR, r);
                minC = Math.min(minC, c);
                maxC = Math.max(maxC, c);
            }
            bw.write((maxR - minR) * (maxC - minC) + "\n");
        }

        bw.close();
    }
}