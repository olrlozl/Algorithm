import java.io.*;
import java.util.*;

public class Solution {
    public static class Edge implements Comparable<Edge> {
        int v;
        long cost;

        public Edge (int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost > 0 ? 1 : -1; // cost 기준 오름차순 정렬
        }
    }

    public static long prim (ArrayList<Edge>[] graph, int start, int N) {
        long cost = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N];

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) { // 우선순위 큐가 빌 때 까지
            Edge now = pq.poll(); // 우선순위 큐에서 정점 하나를 꺼내고

            if (visit[now.v]) continue; // 이미 방문한 정점이라면 건너뛰기

            // 아직 방문하지 않은 정점이라면
            visit[now.v] = true; // 방문처리
            cost += now.cost; // 가중치 합하기

            for (Edge next : graph[now.v]) { // 이웃한 정점 중에서
                if (!visit[next.v]) { // 아직 방문하지 않은 정점이 있다면
                    pq.add(new Edge(next.v, next.cost)); // 우선순위 큐에 넣기
                }
            }
        }
        return cost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine()); // 섬의 개수

            int[] x = new int[N]; // x 좌표
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) x[i] = Integer.parseInt(st.nextToken());

            int[] y = new int[N]; // y 좌표
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) y[i] = Integer.parseInt(st.nextToken());

            double E = Double.parseDouble(br.readLine()); // 환경 부담 세율

            ArrayList<Edge>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        graph[i].add(new Edge(j, (long) (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2))));
                    }
                }
            }
            bw.write("#" + t + " " + Math.round(E * prim(graph, 0, N)) + "\n");
        }
        bw.close();
    }
}