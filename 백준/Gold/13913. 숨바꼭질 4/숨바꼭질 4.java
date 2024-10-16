import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] visit = new int[100001];
        int[] parent = new int[100001];
        for (int i = 0; i < 100001; i++) parent[i] = i;

        queue.add(N);

        while(!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                int now = queue.poll();
                if (now == K) break;

                int[] move = {now - 1, now + 1, now * 2};
                for (int next : move) {
                    if (0 <= next && next <= 100000 && visit[next] == 0 && next != N) {
                        queue.add(next);
                        visit[next] = visit[now] + 1;
                        parent[next] = now;
                    }
                }
            }
        }

        bw.write(visit[K] + "\n");
        find(parent, K);
        bw.close();
    }

    public static void find(int[] parent, int x) throws IOException {
        if (x != parent[x]) find(parent, parent[x]);
        bw.write(x + " ");
    }
}