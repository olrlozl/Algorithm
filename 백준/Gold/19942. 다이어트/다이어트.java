import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int mp;
    public static int mf;
    public static int ms;
    public static int mv;
    public static int[][] arr;
    public static boolean[] select;
    public static int minC = Integer.MAX_VALUE;
    public static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        arr = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new boolean[N];
        subset(0);

        bw.write((minC == Integer.MAX_VALUE ? -1 : minC) + "\n" + result);
        bw.close();
    }

    public static void subset(int idx) {
        if (idx == N) { // 기저조건
            isCheck();
            return;
        }
        select[idx] = true; // 현재 인덱스 선택 O
        subset(idx + 1); // 다음 인덱스 재귀
        select[idx] = false; // 현재 인덱스 선택 X
        subset(idx + 1); // 다음 인덱스 재귀
    }

    public static void isCheck() {
        int sumP = 0, sumF = 0, sumS = 0, sumV = 0, sumC = 0;
        for (int i = 0; i < N; i++) {
            if (select[i]) {
                sumP += arr[i][0];
                sumF += arr[i][1];
                sumS += arr[i][2];
                sumV += arr[i][3];
                sumC += arr[i][4];
            }
        }

        if (sumP < mp || sumF < mf || sumS < ms || sumV < mv || sumC > minC) return;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) if (select[i]) sb.append((i + 1) + " ");
        if (sumC == minC) if (result.compareTo(sb.toString()) < 0) return;
        minC = sumC;
        result = sb.toString();
    }
}