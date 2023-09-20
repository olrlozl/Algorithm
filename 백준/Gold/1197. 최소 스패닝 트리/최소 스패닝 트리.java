import java.io.*;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node> {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // cost기준 오름차순 정렬
        }
    }

    public static int prim(ArrayList<Node>[] graph, int start, int n) {
        int cost = 0; // 가중치의 합
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위큐 (Edge의 cost기준 오름차순 정렬)
        boolean[] visit = new boolean[n + 1]; // 방문 여부

        pq.add(new Node(start, 0)); // 시작 정점을 우선순위큐에 넣기

        while (!pq.isEmpty()) { // 우선순위큐가 빌 때 까지
            Node edge = pq.poll(); // 우선순위큐에서 정점 하나를 꺼내고

            if (visit[edge.v]) continue; // 이미 방문했다면 건너뛰기

            // 아직 방문하지 않았다면
            visit[edge.v] = true; // 방문 처리
            cost += edge.cost; // 가중치 더해주기

            for (Node e : graph[edge.v]) { // 이웃한 정점 중에서
                if (!visit[e.v]) { // 방문하지 않은 정점이 있다면
                    pq.add(e); // 우선순위 큐에 넣기
                }
            }
        }
        return cost; // 가중치의 합 반환
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 정점 a
            int b = Integer.parseInt(st.nextToken()); // 정점 b
            int c = Integer.parseInt(st.nextToken()); // 가중치

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        bw.write(prim(graph, 1, n) + "");
        bw.close();
    }
}