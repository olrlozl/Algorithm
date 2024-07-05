import java.io.*;
import java.util.*;

public class Main {
    public static long[] total;
    public static long[] pat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 버거 레벨
        long x = Long.parseLong(st.nextToken()); // 먹은 장 수

        total = new long[n + 1]; // 레벨별 버거 총 길이
        pat = new long[n + 1]; // 레벨별 패티 수
        total[0] = 1;
        pat[0] = 1;

        for (int i = 1; i <= n; i++) {
            total[i] = 1 + total[i - 1] + 1 + total[i - 1] + 1;
            pat[i] = pat[i - 1] + 1 + pat[i - 1];
        }

        bw.write(recursion(n, x) + "");
        bw.close();
    }

    public static long recursion(int n, long x) {
        if (n == 0) {
            if (x == 0) return 0;
            else if (x == 1) return 1;
        }
        if (x == 1) {
            return 0;
        }
        else if (x <= 1 + total[n - 1]) {
            return recursion(n - 1, x - 1);
        }
        else if (x == 1 + total[n - 1] + 1) {
            return pat[n - 1] + 1;
        }
        else if (x <= 1 + total[n - 1] + 1 + total[n - 1]) {
            return pat[n - 1] + 1  + recursion(n - 1, x - (1 + total[n - 1] + 1));
        }
        else {
            return pat[n];
        }
    }
}