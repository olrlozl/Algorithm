import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static long T;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Long.parseLong(st.nextToken());

        int[] sharks = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sharks[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(sharks);

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int shark : sharks) {
            if (shark < T) stack.push(shark);
            else queue.offer(shark);
        }

        for (int i = 0; i < K; i++) {
            if (stack.isEmpty()) break;
            T += stack.pop();

            while (!queue.isEmpty() && queue.peek() < T) {
                stack.push(queue.poll());
            }
        }

        bw.write(T + "");
        bw.close();
    }
}