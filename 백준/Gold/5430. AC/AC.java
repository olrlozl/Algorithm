import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        tc: for (int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            Deque<Integer> deque = new LinkedList<>();
            boolean isRight = false;
            for (int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            for (char ch : p.toCharArray()) {
                if (ch == 'R') {
                    isRight = !isRight;
                } else {
                    if (deque.isEmpty()) {
                        bw.write("error\n");
                        continue tc;
                    }
                    if (isRight) deque.pollLast();
                    else deque.pollFirst();
                }
            }

            bw.write("[");
            if (!deque.isEmpty()) {
                if (isRight) {
                    bw.write(deque.pollLast() + "");
                    while (!deque.isEmpty()) {
                        bw.write("," + deque.pollLast());
                    }
                } else {
                    bw.write(deque.pollFirst() + "");
                    while (!deque.isEmpty()) {
                        bw.write("," + deque.pollFirst());
                    }
                }
            }
            bw.write("]\n");
        }
        bw.close();
    }
}