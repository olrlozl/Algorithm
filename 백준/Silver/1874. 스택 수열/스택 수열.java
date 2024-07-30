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

        for (int i = 1; i <= N; i++) {
            stack.add(i);
            sb.append("+\n");
            while (!stack.isEmpty() && stack.peek() == arr[idx]) {
                stack.pop();
                sb.append("-\n");
                idx++;
            }
        }

        if (!stack.isEmpty()) bw.write("NO");
        else bw.write(sb.toString());

        bw.close();
    }
}