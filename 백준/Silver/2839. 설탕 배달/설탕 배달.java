import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        int[] kind = {3, 5};

        for (int i = 0; i < kind.length; i++) {
            for (int j = kind[i]; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - kind[i]] + 1);
            }
        }
        
        bw.write((dp[n] == 987654321 ? -1 : dp[n]) + "");
        bw.close();
    }
}