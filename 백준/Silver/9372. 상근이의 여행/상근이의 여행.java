import java.io.*;
import java.util.*;

public class Main {
    // 유니온 (두 그래프 합치기)
    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    // 파인드 (부모 노드 찾기)
    public static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return find(parent, parent[x]);
    }

    // 크루스칼 (최소 신장 트리 알고리즘)
    public static int kruskal (int[][] graph, int[] parent, int M) {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (find(parent, graph[i][0]) != find(parent, graph[i][1])) {
                union(parent, graph[i][0], graph[i][1]);
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 국가 수
            int M = Integer.parseInt(st.nextToken()); // 비행기 종류

            // 비행기 스케줄
            int[][] graph = new int[M][2];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                graph[i][0] = Integer.parseInt(st.nextToken());
                graph[i][1] = Integer.parseInt(st.nextToken());
            }

            // 부모노드 초기화
            int[] parent = new int[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;

            // 크루스칼 함수 호출
            bw.write(kruskal(graph, parent, M) + "\n");
        }
        bw.close();
    }
}