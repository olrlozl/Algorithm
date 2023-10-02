import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][][] arr = new int[21][21][21];

        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if (i == 0 || j == 0 || k == 0) arr[i][j][k] = 1;
                    else if (i < j && j < k) arr[i][j][k] = arr[i][j][k-1] + arr[i][j-1][k-1] - arr[i][j-1][k];
                    else arr[i][j][k] = arr[i-1][j][k] + arr[i-1][j-1][k] + arr[i-1][j][k-1] - arr[i-1][j-1][k-1];
                }
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) break;

            int result = 0;

            if (a <= 0 || b <= 0 || c <= 0) result = arr[0][0][0];
            else if (a > 20 || b > 20 || c > 20) result = arr[20][20][20];
            else result = arr[a][b][c];

            bw.write("w(" + a + ", " + b + ", " + c + ") = " + result + "\n");
        }

        bw.close();
    }
}