import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int k = Integer.parseInt(br.readLine()); // 층 (0 ~ k)
            int n = Integer.parseInt(br.readLine()); // 호 (1 ~ n)

            int[][] arr = new int[k + 1][n + 1];
            for (int i = 1; i <= n; i++) arr[0][i] = i;

            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    int sum = 0;
                    for (int l = 1; l <= j; l++) sum += arr[i - 1][l];
                    arr[i][j] = sum;
                }
            }
            bw.write(arr[k][n] + "\n");
        }
        bw.close();



        bw.close();
    }
}