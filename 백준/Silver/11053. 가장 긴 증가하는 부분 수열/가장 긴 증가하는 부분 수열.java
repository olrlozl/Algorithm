import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[n];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            int nowLen = maxLen;
            loop: while (nowLen > 0) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] == nowLen && arr[j] < arr[i]) {
                        dp[i] = nowLen + 1;
                        if (nowLen == maxLen) maxLen++;
                        break loop;
                    }
                }
                nowLen--;
            }
            if (nowLen == 0) dp[i] = 1;
        }

        bw.write(maxLen + "");
        bw.close();
    }
}