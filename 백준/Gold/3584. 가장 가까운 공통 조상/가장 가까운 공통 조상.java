import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            boolean[] visit = new boolean[N + 1];

            for (int j = 0; j < N - 1; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (x > 0) {
                visit[x] = true;
                x = parent[x];
            }

            while (y > 0) {
                if (visit[y]) {
                    bw.write(y + "\n");
                    break;
                }
                y = parent[y];
            }
        }
        bw.close();
    }
}