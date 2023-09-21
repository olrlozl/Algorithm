import java.io.*;
import java.util.*;

public class Solution {
    public static int bfs(ArrayList<Integer>[] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[101];

        queue.add(start);
        visit[start] = true;
        int max = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            max = 0;

            for (int i = 0; i < len; i++) {
                int now = queue.poll();
                max = Math.max(max, now);

                for (int next : graph[now]) {
                    if (!visit[next]) {
                        queue.add(next);
                        visit[next] = true;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
            int start = Integer.parseInt(st.nextToken()); // 시작점

            ArrayList<Integer>[] graph = new ArrayList[101];
            for (int i = 1; i <= 100; i++) graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n / 2; i++) graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
            
            bw.write("#" + t + " " + bfs(graph, start) + "\n");
        }
        bw.close();
    }
}