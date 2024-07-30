import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        int idx = 0;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean pos = true;

        for (int i = 1; i <= N; i++) {
            if (stack.isEmpty() || stack.peek() != arr[idx]) {
                stack.add(i);
                sb.append("+\n");
                continue;
            }
            while (!stack.isEmpty() && stack.peek() == arr[idx]) {
                stack.pop();
                sb.append("-\n");
                idx++;
            }
            stack.add(i);
            sb.append("+\n");
        }

        while (!stack.isEmpty()) {
            if (stack.pop() == arr[idx++]) {
                sb.append("-\n");
            } else {
                bw.write("NO");
                pos = false;
                break;
            }
        }

        if (pos) bw.write(sb.toString());
        bw.close();
    }
}