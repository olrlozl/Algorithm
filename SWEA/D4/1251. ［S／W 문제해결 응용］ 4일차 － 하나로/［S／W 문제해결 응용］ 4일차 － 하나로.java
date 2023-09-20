import java.util.*;
import java.io.*;

public class Solution {

    public static void union (int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]);
    }

    public static long kruskal(long[][] graph, int[] parent) {
        long cost = 0;

        for (int i = 0; i < graph.length; i++) {
            if (find(parent, (int)graph[i][0]) != find(parent, (int)graph[i][1])) {
                cost += graph[i][2];
                union(parent, (int)graph[i][0], (int)graph[i][1]);
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
            int n = Integer.parseInt(br.readLine()); // 섬의 개수

            // x좌표
            long[] x = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) x[i] = Integer.parseInt(st.nextToken());

            // y좌표
            long[] y = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) y[i] = Integer.parseInt(st.nextToken());

            double E = Double.parseDouble(br.readLine()); // 환경 부담 세율

            long[][] graph = new long[n * (n-1) / 2][3];

            for (int i = 0, idx = 0; i < n-1; i++) {
                for (int j = i + 1; j < n; j++) {
                    graph[idx][0] = i; // x좌표
                    graph[idx][1] = j; // y좌표
                    graph[idx++][2] = (long) (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)); // cost
                }
            }

            Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2] >= 0 ? 1 : -1); // cost 기준 오름차순 정렬

            int[] parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;

            bw.write("#" + t + " " + Math.round(E * kruskal(graph, parent)) + "\n");
        }
        bw.close();
    }
}