import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] graph, tree;
    public static int[] parent, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        makeTree(R, -1); // R을 루트로하는 트리 만들기
        countSubtreNodes(R); // 각 정점을 루트로하는 서브트리에 속한 정점의 수 계산

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            bw.write(size[query] + "\n");
        }
        bw.close();
    }

    // curNode를 루트로 하는 트리 만들기 (p curNode의 부모, 부모가 없으면 -1)
    public static void makeTree(int curNode, int p) {
        for (int node: graph[curNode]) {
            if (node != p) {
                tree[curNode].add(node); // currentNode의 자식에 node 추가
                parent[node] = curNode; // node의 부모는 curNode
                makeTree(node, curNode); // [재귀]
            }
        }
    }

    // curNode를 루트로 하는 서브트리에 속한 정점의 수 계산
    public static void countSubtreNodes(int curNode) {
        size[curNode] = 1; // 자신 포함
        for (int node: tree[curNode]) {
            countSubtreNodes(node); // [재귀]
            size[curNode] += size[node];
        }
    }
}