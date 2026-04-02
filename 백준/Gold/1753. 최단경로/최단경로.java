import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        Dijkstra(V, K);
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static void Dijkstra(int V, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] vist = new boolean[V + 1];
        int[] dist = new int[V + 1];

        pq.add(new Node(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int nowIdx = pq.poll().idx;

            for (Node next : graph[nowIdx]) {
                if (dist[next.idx] > dist[nowIdx] + next.cost) {
                    dist[next.idx] = dist[nowIdx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}