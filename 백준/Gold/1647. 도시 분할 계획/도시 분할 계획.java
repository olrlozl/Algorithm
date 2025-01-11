import java.io.*;
import java.util.*;

public class Main {
    public static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int M = Integer.parseInt(st.nextToken()); // 길의 개수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, cost));
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N ; i++) {
            parent[i] = i;
        }

        bw.write(kruskal() + "");
        bw.close();
    }

    public static class Edge implements Comparable<Edge>{
        int a;
        int b;
        int cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    public static int kruskal() {
        int answer = 0;
        int maxCost = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.a) != find(edge.b)) {
                union(edge.a, edge.b);
                answer += edge.cost;
                maxCost = Math.max(maxCost, edge.cost);
            }
        }
        return answer - maxCost;
    }
}