import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 보석 무게
            int V = Integer.parseInt(st.nextToken()); // 보석 가격
            jewels[i] = new Jewel(M, V);
        }

        int[] bags = new int[K]; // 가방에 담을 수 있는 최대 무게
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags); // 가방 무게 오름차순 정렬
        Arrays.sort(jewels); // 보석 무게 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 보석 가격 내림차순
        long answer = 0;

        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && bags[i] >= jewels[j].weight) {
                pq.offer(jewels[j++].cost);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        bw.write(answer + "");
        bw.close();
    }

    public static class Jewel implements Comparable<Jewel>{
        int weight;
        int cost;

        public Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight; // 보석 무게 오름차순 정렬
        }
    }
}