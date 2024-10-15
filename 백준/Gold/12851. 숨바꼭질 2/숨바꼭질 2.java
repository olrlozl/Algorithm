import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] time = new int[100001];

        int cnt = 0;
        boolean flag = false;

        queue.add(N);
        time[N]++;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                int x = queue.poll();
                if (x == K) {
                    flag = true;
                    cnt++;
                }

                int[] next = {x - 1, x + 1, 2 * x};
                for (int n: next) {
                    if (0 <= n && n <= 100000 && (time[n] == 0 || time[n] == time[x] + 1)) {
                        time[n] = time[x] + 1;
                        queue.add(n);
                    }
                }
            }

            if (flag) break;
        }

        bw.write((time[K] - 1) + "\n" + cnt);
        bw.close();
    }
}