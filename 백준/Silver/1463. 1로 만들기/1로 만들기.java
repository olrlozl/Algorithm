import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];

        if (n % 3 == 0) {
            queue.add(n / 3);
            visit[n / 3] = true;
        }
        if (n % 2 == 0) {
            queue.add(n / 2);
            visit[n / 2] = true;
        }
        if (n > 1) {
            queue.add(n - 1);
            visit[n - 1] = true;
        }

        int cnt = 0;

        loop : while (!queue.isEmpty()) {
            int len = queue.size();
            cnt++;
            for (int i = 0; i < len; i++) {
                int now = queue.poll();
                if (now == 1) break loop;
                if (now % 3 == 0 && !visit[now / 3]) {
                    queue.add(now / 3);
                    visit[now / 3] = true;
                }
                if (now % 2 == 0 && !visit[now / 2]) {
                    queue.add(now / 2);
                    visit[now / 2] = true;
                }
                if (now > 1 && !visit[now - 1]) {
                    queue.add(now - 1);
                    visit[now - 1] = true;
                }
            }
        }
        bw.write(cnt + "");
        bw.close();
    }
}