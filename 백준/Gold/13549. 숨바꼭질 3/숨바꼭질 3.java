import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] time = new int[100001];
        Arrays.fill(time, Integer.MAX_VALUE);

        queue.add(N);
        time[N] = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if (0 <= cur - 1 && cur - 1 <= 100000 && time[cur - 1] > time[cur] + 1) {
                queue.add(cur - 1);
                time[cur - 1] = time[cur] + 1;
            }
            if (0 <= cur + 1 && cur + 1 <= 100000 && time[cur + 1] > time[cur] + 1) {
                queue.add(cur + 1);
                time[cur + 1] = time[cur] + 1;
            }
            if (0 <= cur * 2 && cur * 2 <= 100000 && time[cur * 2] > time[cur]) {
                queue.add(cur * 2);
                time[cur * 2] = time[cur];
            }
        }

        bw.write(time[K] + "");
        bw.close();
    }


}