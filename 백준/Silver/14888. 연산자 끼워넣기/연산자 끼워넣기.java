import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] nums;
    public static int[] operators;
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        operators = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(nums[0], 1);

        bw.write(max + "\n" + min);
        bw.close();
    }

    public static void dfs(int cur, int numIdx) {
        if (numIdx == N) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                if (i == 0) dfs(cur + nums[numIdx], numIdx + 1);
                if (i == 1) dfs(cur - nums[numIdx], numIdx + 1);
                if (i == 2) dfs(cur * nums[numIdx], numIdx + 1);
                if (i == 3) dfs(cur / nums[numIdx], numIdx + 1);
                operators[i]++;
            }
        }

    }
}