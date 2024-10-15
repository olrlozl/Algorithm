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
        operators = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0, idx = 0; i < 4; i++) {
            int x = Integer.parseInt(st.nextToken());
            for (int j = 0; j < x; j++) {
                operators[idx++] = i;
            }
        }

        int[] tgt = new int[N - 1];
        boolean[] visit = new boolean[N - 1];
        duplicate_permutation(tgt, visit, 0);

        bw.write(max + "\n" + min);
        bw.close();
    }

    public static void duplicate_permutation(int[] tgt, boolean[] visit, int tgtIdx) {
        if (tgtIdx == N - 1) {
            circulate(tgt);
            return;
        }

        boolean[] select = new boolean[4];
        for (int i = 0; i < N - 1; i++) {
            if (!visit[i] && !select[operators[i]]) {
                tgt[tgtIdx] = operators[i];
                select[operators[i]] = true;
                visit[i] = true;
                duplicate_permutation(tgt, visit, tgtIdx + 1);
                visit[i] = false;
            }
        }
    }

    public static void circulate(int[] tgt) {
        int result = nums[0];
        for (int i = 0; i < N - 1; i++) {
            if (tgt[i] == 0) result += nums[i + 1];
            else if (tgt[i] == 1) result -= nums[i + 1];
            else if (tgt[i] == 2) result *= nums[i + 1];
            else if (tgt[i] == 3) result /= nums[i + 1];
        }
        min = Math.min(min, result);
        max = Math.max(max, result);
    }
}