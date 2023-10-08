import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[] arr;
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void Permutation(int[] src, int[] tgt, boolean[] select, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            int cal = arr[0];
            for (int i = 0; i < n - 1; i++) {
                if (tgt[i] == 0) cal += arr[i + 1];
                else if (tgt[i] == 1) cal -= arr[i + 1];
                else if (tgt[i] == 2) cal *= arr[i + 1];
                else if (tgt[i] == 3) cal /= arr[i + 1];
            }
            max = Math.max(max, cal);
            min = Math.min(min, cal);
            return;
        }

        boolean[] visit = new boolean[4];
        for (int i = 0; i < n - 1; i++) {
            if (!select[i] && !visit[src[i]]) {
                tgt[tgtIdx] = src[i];
                visit[src[i]] = true;
                select[i] = true;
                Permutation(src, tgt, select, tgtIdx + 1);
                select[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        // + - * /
        int[] src = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0, idx = 0; i < 4; i++) {
            int c = Integer.parseInt(st.nextToken());
            for (int j = 0; j < c; j++) {
                src[idx++] = i;
            }
        }

        int[] tgt = new int[n - 1];
        boolean[] select = new boolean[n - 1];

        Permutation(src, tgt, select, 0);

        bw.write(max + "\n" + min);
        bw.close();
    }
}