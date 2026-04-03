import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
        PriorityQueue<Integer> right = new PriorityQueue<>(); // minHeap

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (left.size() == right.size()) left.add(x);
            else right.add(x);
            
            if (!right.isEmpty() && left.peek() > right.peek()) {
                right.add(left.poll());
                left.add(right.poll());
            }
            bw.write(left.peek() + "\n");
        }
        bw.close();
    }
}