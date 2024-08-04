import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(st.nextToken());
                queue.add(v);
                pq.add(v);
            }

            int cnt = 0;
            while(!queue.isEmpty()) {
                if (queue.peek() == pq.peek()) {
                    cnt++;
                    if (M == 0) break;
                    M--;
                    queue.poll();
                    pq.poll();

                } else {
                    queue.add(queue.poll());
                    M = (M == 0 ? queue.size() - 1 : M - 1);
                }
            }
            bw.write(cnt + "\n");
        }
        bw.close();
    }
}