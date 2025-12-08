import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        func(0);
    }

    public static void func(int num) throws IOException {
        if (num == 81) {
            for (int i = 0; i < 9; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]);
                }
                bw.write(sb + "\n");
            }
            bw.close();
            System.exit(0);
        }

        int r = num / 9;
        int c = num % 9;

        if (arr[r][c] > 0) {
            func(num + 1);
            return;
        }

        for (int n = 1; n <= 9; n++) {
            if (isPossible(r, c, n)) {
                arr[r][c] = n;
                func(num + 1);
                arr[r][c] = 0;
            }
        }
    }

    public static boolean isPossible(int r, int c, int n) {
        for (int i = 0; i < 9; i++) {
            if (arr[r][i] == n) return false;
            if (arr[i][c] == n) return false;
        }

        int sr = r - r % 3;
        int sc = c - c % 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (arr[i][j] == n) return false;
            }
        }
        return true;
    }
}