import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Space> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a >= b) K--;
            else pq.add(new Space(a, b - a));
        }

        int result = 0;
        if (K > 0) {
            for (int i = 0; i < K; i++) {
                result = pq.poll().need;
            }
        }

        bw.write(result + "");
        bw.close();
    }

    public static class Space implements Comparable<Space> {
        int price;
        int need;

        public Space(int price, int need) {
            this.price = price;
            this.need = need;
        }

        @Override
        public int compareTo(Space o) {
            return this.need - o.need;
        }
    }
}