import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void find (int[] parent, int x) throws IOException {
        if (x != parent[x]) find(parent, parent[x]);
        bw.write(x + " ");
    }
    
    public static void bfs (int n, int k) throws IOException {

        Queue<Integer> queue = new LinkedList<>();
        int[] visit = new int[100001];
        int[] parent = new int[100001];
        for (int i = 1; i <= 100000; i++) parent[i] = i;

        queue.add(n);

        loop : while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == k) break;
            int[] move = {now - 1, now + 1, now * 2};
            for (int next : move) {
                if (0 <= next && next <= 100000 && visit[next] == 0 && next != n) {
                    queue.add(next);
                    visit[next] = visit[now] + 1;
                    parent[next] = now;
                    if (next == k) break loop;
                }
            }
        }
        bw.write(visit[k] + "\n");
        find(parent, k);
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n, k);
    }
}