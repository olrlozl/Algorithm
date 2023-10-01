import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) queue.add(i);

        bw.write("<");

        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) queue.add(queue.poll());
            bw.write(queue.poll() + ", ");
        }

        bw.write(queue.poll() + ">");
        bw.close();
    }
}