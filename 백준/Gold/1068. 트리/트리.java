import java.io.*;
import java.util.*;

public class Main {
    public static int leaf = 0;

    public static void dfs(ArrayList<Integer>[] graph, boolean[] visit, int node) {
        visit[node] = true; // 현재 노드 방문 처리

        int cnt = 0; // 삭제되지 않은 이웃한 자식 개수

        for (int child : graph[node]) { // 자식 노드 중에서
            if (!visit[child]) { // 방문하지 않은 노드가 있다면
                cnt++;
                dfs(graph, visit, child); // dfs 재귀 호출
            }
        }

        if (cnt == 0) leaf++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 트리 노드 개수

        ArrayList<Integer>[] graph = new ArrayList[n]; // 부모 기준 으로, 자식 노드 담아둘 그래프
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int root = 0; // 루트 노드

        st = new StringTokenizer(br.readLine());
        for (int child = 0; child < n; child++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) root = child;
            else graph[parent].add(child);
        }

        int cut = Integer.parseInt(br.readLine()); // 삭제할 노드

        boolean[] visit = new boolean[n];
        visit[cut] = true; // 삭제할 노드 이하의 노드들을 방문하지 못하도록 미리 방문 처리해두기

        if (cut != root) { // 삭제할 노드가 루트 노드라면 leaf = 0 이기 때문
            dfs(graph, visit, root); // 루트 노드를 시작으로 dfs 탐색
        }

        bw.write(leaf + "");
        bw.close();
    }
}