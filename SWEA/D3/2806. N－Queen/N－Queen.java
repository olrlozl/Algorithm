import java.io.*;

public class Solution {
    public static int n;
    public static int cnt;

    public static void permutation(int[] tgt, boolean[] visit, int r) {
        if (r == n) {
            cnt++;
            return;
        }

        loop: for (int c = 0; c < n; c++) {
            if (!visit[c]) {
                for (int i = 0; i < r; i++) if (Math.abs(r - i) == Math.abs(c - tgt[i])) continue loop; // 대각선이면 패스
                tgt[r] = c;
                visit[c] = true;
                permutation(tgt, visit, r + 1);
                visit[c] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
        n = Integer.parseInt(br.readLine());

        int[] tgt = new int[n];
        boolean[] visit = new boolean[n];

        cnt = 0;
        permutation(tgt, visit, 0);

        bw.write("#" + t + " " + cnt + "\n");
    }
    
    bw.close();
    
    }
}