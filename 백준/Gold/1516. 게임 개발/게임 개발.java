import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] cnt = new int[N + 1];

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                graph[x].add(i);
                cnt[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] preTime = new int[N + 1]; // 각 건물을 짓기 전까지 걸린 시간

        for (int i = 1; i <= N; i++) {
            if (cnt[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (--cnt[next] == 0) {
                    queue.add(next);
                }
                preTime[next] = Math.max(preTime[next], preTime[now] + time[now]);
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(preTime[i] + time[i]);
        }
    }
}