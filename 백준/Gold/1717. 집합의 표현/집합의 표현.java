import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        bw.close();
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[x] = y;
        else if (x > y) parent[y] = x;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }
}