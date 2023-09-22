import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] graph;
    public static int min = Integer.MAX_VALUE;

    public static void bfs (boolean[] tgt, boolean[] visit, int n, int i) {
        Queue<Integer> queue = new LinkedList<>();
        boolean state = tgt[i];

        queue.add(i);
        visit[i] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (!visit[next] && tgt[next] == state) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }
    }
    public static void combination (boolean[] tgt, int[] vote, int n, int r, int idx, int cnt) {
        if (cnt == r) {
            boolean[] visit = new boolean[n + 1];
            int count = 0; // bfs 호출 횟수

            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    bfs(tgt, visit, n, i);
                    count++;
                }

            }
            if (count == 2) { // 두 그룹으로 나눠지고 각각의 그룹이 서로 연결되어있다면
                int A = 0; // A구역 인원 수
                int B = 0; // B구역 인원 수
                for (int i = 1; i <= n; i++) {
                    if (tgt[i]) A += vote[i];
                    else B += vote[i];
                }
                min = Math.min(min, Math.abs(A - B));
            }

            return;
        }

        if (idx == n + 1) return;

        tgt[idx] = true;
        combination(tgt, vote, n, r, idx + 1, cnt + 1);
        tgt[idx] = false;
        combination(tgt, vote, n, r, idx + 1, cnt);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 구역 개수
        int[] vote = new int[n + 1]; // 인구 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) vote[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int r = 1; r <= n / 2; r++) {
            boolean[] tgt = new boolean[n + 1];
            combination(tgt, vote, n, r, 1, 0);
        }

        bw.write((min != Integer.MAX_VALUE ? min : -1) + "");
        bw.close();
    }
}