import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 1; t <= 5; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] memo = new int[n + 1];
            memo[1] = 1;
            for(int i = 2; i <= n; i++) memo[i] = memo[i - 1] + memo[i - 2];
            bw.write(memo[n] + "\n");
        }
        bw.close();
    }
}