import java.io.*;
import java.util.*;

public class Main {

    public static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int find (int[] parent, int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]); // 부모노드 대신 루트노드로 재설정
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 초기 집합: {0}, {1}, {2}, ... , {n}
        int m = Integer.parseInt(st.nextToken()); // 연산 개수

        // 부모노드 초기화
        int[] parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) parent[i] = i;

        // m개의 연산
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int od = Integer.parseInt(st.nextToken()); // 연산 종류
            int a = Integer.parseInt(st.nextToken()); // a 집합
            int b = Integer.parseInt(st.nextToken()); // b 집합

            if (od == 0) { // 합집합
                union(parent, a, b);
            } else if (od == 1) { // 같은 집합에 포함 되어 있는지 확인
                bw.write(find(parent, a) == find(parent, b) ? "YES\n" : "NO\n");
            }
        }
        bw.close();
    }
}