import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        // 각 노드로의 진입 차수
        int[] edgeCnt = new int[n + 1];

        // 각 노드가 집입하는 노드들의 정보
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            edgeCnt[b]++;
        }

        Queue<Integer> queue = new LinkedList<>(); // 큐

        // 진입 차수의 값이 0인 노드가 있다면 큐에 넣기
        for (int i = 1; i <= n; i++)
            if (edgeCnt[i] == 0) queue.add(i);

        while (!queue.isEmpty()) { // 큐가 빌 때 까지
            int now = queue.poll(); // 큐에서 노드 하나를 꺼내고
            bw.write(now + " ");
            for (int next : graph[now]) { // 진입 가능한 다음 노드가 있다면
                edgeCnt[next]--; // 진입 차수 감소 후
                if (edgeCnt[next] == 0) { // 진입 차수의 값이 0이 되었다면
                    queue.add(next); // 큐에 넣기
                }
            }
        }

        bw.close();
    }
}