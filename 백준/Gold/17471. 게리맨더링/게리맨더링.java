import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[] arr;
    public static ArrayList<Integer>[] graph;
    public static int min = Integer.MAX_VALUE;

    public static void bfs(boolean[] tgt, boolean[] visit, int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (!visit[next] && tgt[next] == tgt[start]) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }
    }

    public static void Combination(boolean[] tgt, int r, int idx, int cnt) {
        if (cnt == r) {
            boolean[] visit = new boolean[n + 1];
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    bfs(tgt, visit, i);
                    count++;
                }
            }
            if (count == 2) {
                int total = 0;
                for (int i = 1; i <= n; i++) {
                    if (tgt[i]) total += arr[i];
                    else total -= arr[i];
                }
                min = Math.min(min, Math.abs(total));
            }
            return;
        }

        if (idx == tgt.length) return;

        tgt[idx] = true;
        Combination(tgt, r, idx + 1, cnt + 1);
        tgt[idx] = false;
        Combination(tgt, r, idx + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 구역의 개수

        arr = new int[n + 1]; // 각 구역의 인구 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1]; // 각 구역과 인접한 구역의 정보
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // n개 구역중 r개 구역을 뽑자
        for (int r = 1; r <= n / 2; r++) {
            Combination(new boolean[n + 1], r, 1, 0);
        }

        bw.write((min == Integer.MAX_VALUE ? -1 : min) + "");
        bw.close();
    }
}