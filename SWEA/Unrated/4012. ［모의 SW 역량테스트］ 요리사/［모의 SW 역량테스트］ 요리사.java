import java.io.*;
import java.util.*;

public class Solution {

    public static int result;

    public static void combination (int idx, int cnt, int[][] arr, boolean[] select) {
        if (cnt == arr.length / 2) {
            // 완성된 조합 계산
            int sumTrue = 0; // A음식의 맛
            int sumFalse = 0; // B음식의 맛

            for (int i = 0; i < arr.length; i++) {
                if (select[i]) { // A음식
                    for (int j = i+1; j < arr.length; j++) {
                        if (select[j]) {
                            sumTrue += arr[i][j] + arr[j][i];
                        }
                    }
                } else { // B음식
                    for (int j = i+1; j < arr.length; j++) {
                        if (!select[j]) {
                            sumFalse += arr[i][j] + arr[j][i];
                        }
                    }
                }
            }
            result = Math.min(result, Math.abs(sumTrue - sumFalse)); // A음식과 B음식 맛의 차이의 최소값
            return;
        }

        if (idx == arr.length) return;

        select[idx] = true;
        combination(idx+1, cnt+1, arr, select);
        select[idx] = false;
        combination(idx+1, cnt, arr, select);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[] select = new boolean[N];
            result = Integer.MAX_VALUE;
            combination(0,0, arr, select);

            bw.write("#" + t + " " + result + "\n");
        }
    bw.close();
    }
}