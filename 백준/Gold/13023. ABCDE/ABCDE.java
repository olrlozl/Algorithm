import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visit;
    public static int result;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        visit = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        result = 0;

        for (int i = 0; i < N; i++) {
            dfs(i, 0);
        }

        bw.write(result + "");
        bw.close();
    }

    public static void dfs(int v, int depth) {
        if (result == 1) return;

        if (depth == 4) {
            result = 1;
            return;
        }

        visit[v] = true;

        for (int i : graph[v]) {
            if (!visit[i]) {
                dfs(i, depth + 1);
            }
        }

        visit[v] = false;
    }
}