import java.io.*;
import java.util.*;

public class Main {
    // PriorityQueue에서 remove하면 시간초과나기 때문에, 요소의 개수를 담은 HashMap을 활용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            Map<Long, Integer> map = new HashMap<>();
            PriorityQueue<Long> min = new PriorityQueue<>();
            PriorityQueue<Long> max = new PriorityQueue<>(Collections.reverseOrder());

            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char order = st.nextToken().charAt(0);
                Long num = Long.parseLong(st.nextToken());

                if (order == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    min.add(num);
                    max.add(num);
                } else {
                    if (map.isEmpty()) continue;
                    if (num == 1) removeMap(max, map);
                    else removeMap(min, map);
                }
            }
            if (map.isEmpty()) bw.write("EMPTY\n");
            else {
                Long x = removeMap(max, map);
                bw.write(x + " " + (map.isEmpty() ? x : removeMap(min, map)) + "\n");
            }
        }
        bw.close();
    }

    public static Long removeMap(PriorityQueue<Long> queue, Map<Long, Integer> map) {
        Long x;
        while (true) {
            x = queue.poll();
            int cnt = map.getOrDefault(x, 0);
            if (cnt == 0) continue;
            if (cnt == 1) map.remove(x);
            else map.put(x, map.get(x) - 1);
            break;
        }
        return x;
    }
}