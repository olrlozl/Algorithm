import java.io.*;
import java.util.*;

public class Main {
    public static int[][] arr;
    public static int[] m1_0_p1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        m1_0_p1 = new int[3];
        simulation(0, 0, N);
        for (int i = 0; i < 3; i++) bw.write(m1_0_p1[i] + "\n");
        bw.close();
    }

    public static boolean isSame(int r, int c, int len) {
        int s = arr[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (s != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void simulation(int r, int c, int len) {
        if (isSame(r, c, len)) {
            m1_0_p1[arr[r][c] + 1]++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                simulation(r + i * len/3, c + j * len/3, len/3);
            }
        }
    }
}