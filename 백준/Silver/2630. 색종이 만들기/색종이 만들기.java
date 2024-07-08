import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr;
    public static int cnt0 = 0;
    public static int cnt1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0, 0, N);
        bw.write(cnt0 + "\n");
        bw.write(cnt1 + "");
        bw.close();
    }

    public static void divide(int r, int c, int len) {
        if (whatColor(r, c, len) == 0) {
            cnt0++;
        } else if (whatColor(r, c, len) == 1) {
            cnt1++;
        } else {
            divide(r, c, len / 2);
            divide(r, c + len / 2 , len / 2);
            divide(r + len / 2, c, len / 2);
            divide(r + len / 2, c + len / 2 , len / 2);
        }
    }

    public static int whatColor(int r, int c, int len) {
        int color = arr[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (arr[i][j] != color) {
                    return 2;
                }
            }
        }
        return color;
    }
}