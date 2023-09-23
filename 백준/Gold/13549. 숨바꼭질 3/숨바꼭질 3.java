import java.io.*;
import java.util.*;

public class Main {

    public static int bfs (int n, int k) {
        int[] time = new int[100001];
        Arrays.fill(time, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(n);
        time[n] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (0 <= now * 2 && now * 2 < 100001 && time[now * 2] > time[now]) {
                queue.add(now * 2);
                time[now * 2] = time[now];
            }

            if (0 <= now - 1 && now - 1 < 100001 && time[now - 1] > time[now] + 1) {
                queue.add(now - 1);
                time[now - 1] = time[now] + 1;
            }

            if (0 <= now + 1 && now + 1 < 100001 && time[now + 1] > time[now] + 1) {
                queue.add(now + 1);
                time[now + 1] = time[now] + 1;
            }
        }
        return time[k];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bw.write(bfs(n, k) + "");
        bw.close();
    }
}