import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            bw.write("#" + t + " ");
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // 정점 개수
            int e = Integer.parseInt(st.nextToken()); // 간선 개수

            ArrayList<Integer>[] graph = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

            int[] edgeCnt = new int[v + 1]; // 간선 진입 차수

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                edgeCnt[b]++; // 간선 진입 차수 증가
            }

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= v; i++)
                if (edgeCnt[i] == 0) queue.add(i); // 진입 차수의 값이 0인 노드를 큐에 넣기

            while (!queue.isEmpty()) { // 큐가 빌 때 까지
                int now = queue.poll(); // 큐에서 노드 하나를 꺼내고
                bw.write(now + " ");
                for (int next : graph[now]) { // 진입 가능한 다음 노드가 있다면
                    edgeCnt[next]--; // 진입 차수 감소 후
                    if (edgeCnt[next] == 0) { // 진입차수의 값이 0이 되었다면
                        queue.add(next); // 큐에 넣기
                    }
                }
            }
            bw.newLine();
        }
        bw.close();
    }
}