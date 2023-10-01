import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 물건 개수
            int k = Integer.parseInt(st.nextToken()); // 가방 부피

            int[] volume = new int[n + 1]; // 물건 부피
            int[] cost = new int[n + 1]; // 물건 가치

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                volume[i] = Integer.parseInt(st.nextToken()); // 물건 부피
                cost[i] = Integer.parseInt(st.nextToken()); // 물건 가치
            }

            int[][] arr = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                for (int w = 1; w <= k; w++) {
                    if (volume[i] > w) {
                        arr[i][w] = arr[i-1][w];
                    } else {
                        arr[i][w] = Math.max(arr[i-1][w], arr[i-1][w-volume[i]] + cost[i]);
                    }
                }
            }
            bw.write("#" + t + " " + arr[n][k] + "\n");
        }
        bw.close();
    }
}