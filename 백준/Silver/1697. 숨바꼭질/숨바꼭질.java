import java.io.*;
import java.util.*;

public class Main {

	public static void bfs(int[] visit, int N, int K) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N); // 수빈의 시작 위치를 큐에 넣고

		while (!queue.isEmpty()) { // 큐가 빌 때 까지
			int n = queue.poll(); // 큐에서 원소 하나를 꺼내고
			if (n == K) break; // 동생 위치까지 도달했다면 break
			int[] move = { n - 1, n + 1, n * 2 }; // 이동가능한 위치 중에서
			for (int m : move) {
				if (0 <= m && m <= 100000 && visit[m] == 0 && m != N) { // 방문하지 않은 위치가 있다면 (시작 위치 제외)
					queue.add(m); // 큐에 넣고
					visit[m] = visit[n] + 1; // 방문 처리
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치

		int[] visit = new int[100001]; // 각 지점별 방문 시간

		bfs(visit, N, K);

		bw.write(visit[K] + "");
		bw.close();

	}

}