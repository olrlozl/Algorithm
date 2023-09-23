import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int[] edgeCnt = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            edgeCnt[b]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (edgeCnt[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            bw.write(now + " ");
            for (int next : graph[now]) {
                edgeCnt[next]--;
                if (edgeCnt[next] == 0) queue.add(next);
            }
        }
        bw.close();
    }
}