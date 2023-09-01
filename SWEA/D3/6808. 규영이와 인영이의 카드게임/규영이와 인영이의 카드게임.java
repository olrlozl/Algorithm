import java.io.*;
import java.util.*;

public class Solution {
    public static int[] arr1; // 규영이 카드 (순서고정)
    public static int[] src = new int[9]; // 인영이 카드 종류

    public static int[] arr2 = new int[9]; // 인영이 카드 조합
    public static boolean[] select = new boolean[9]; // 인영이 카드 선택 여부

    public static int win;
    public static int lose;

    public static void permutation (int idx) {
        if (idx == 9) {
            int score1 = 0; // 규영이 점수
            int score2 = 0; // 인영이 점수

            for (int i = 0; i < 9; i++) {
                if (arr1[i] > arr2[i]) score1 += arr1[i] + arr2[i];
                else score2 += arr1[i] + arr2[i];
            }

            if (score1 > score2) win ++;
            else lose++;

            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!select[i]) {
                arr2[idx] = src[i];
                select[i] = true;
                permutation(idx + 1);
                select[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 규영이 카드 (순서 고정)
            arr1 = new int[9];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) arr1[i] = Integer.parseInt(st.nextToken());

            // 인영이 카드 종류
            for (int i = 1, idx = 0; i <= 18; i++) {
                boolean contain = false;
                for (int j = 0; j < 9; j++) {
                    if (i == arr1[j]) {
                        contain = true;
                        break;
                    }
                }
                if (!contain) src[idx++] = i;
            }

            win = 0; // 규영이가 이기는 횟수
            lose = 0; // 인영이가 이기는 횟수

            permutation(0); // 인영이가 카드를 내는 9! 순서에 따라 승부 결정하기

            bw.write("#" + t + " " + win + " " + lose + "\n");
        }
        bw.close();
    }
}