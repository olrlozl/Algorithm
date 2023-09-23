import java.io.*;
import java.util.*;

public class Main {

    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]);
    }

    public static double kruskal(double[][] graph, int n) {
        double cost = 0;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n * (n - 1) / 2; i++) {
            if (find(parent, (int)graph[i][0]) != find(parent, (int)graph[i][1])) {
                union(parent, (int)graph[i][0], (int)graph[i][1]);
                cost += graph[i][2];
            }
        }

        return cost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 별의 개수

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        double[][] graph = new double[n * (n - 1) / 2][3];

        for (int i = 0, idx = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[idx][0] = i;
                graph[idx][1] = j;
                graph[idx++][2] = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
            }
        }

        Arrays.sort(graph, (o1, o2) -> o1[2] > o2[2] ? 1 : -1); // cost 기준 오름차순 정렬

        System.out.printf("%.2f", kruskal(graph, n));
    }
}