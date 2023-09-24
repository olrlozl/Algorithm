import java.io.*;
import java.util.*;

public class Main {

    public static int[] time = new int[100001];
    public static int count = 0;

    public static void bfs (int n, int k) {
        if (n == k) {
            count = 1;
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int[] move = {now - 1, now + 1, 2 * now};

            for (int next : move) {
                if (0 <= next && next < 100001 && (time[next] == 0 || time[next] == time[now] + 1) && next != n) {
                    queue.add(next);
                    time[next] = time[now] + 1;
                    if (next == k) count++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n, k);

        bw.write(time[k] + "\n");
        bw.write(count + "");

        bw.close();
    }
}