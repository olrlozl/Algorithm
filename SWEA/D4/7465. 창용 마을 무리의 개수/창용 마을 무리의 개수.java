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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;


            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (find(parent, a) != find(parent, b)) union(parent, a, b);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) set.add(find(parent, i));

            bw.write("#" + t + " " + set.size() +  "\n");
        }
        bw.close();

    }
}