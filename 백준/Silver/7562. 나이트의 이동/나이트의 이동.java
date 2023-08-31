import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	// 1시, 2시, 4시, 5시, 7시, 8시, 10시, 11시
	public static int[] dR = { -2, -1, 1, 2, 2, 1, -1, -2 };
	public static int[] dC = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void bfs(int[][] arr, int curR, int curC, int goalR, int goalC, int N) {
		Queue<Point> queue = new LinkedList<>(); // 큐
		queue.add(new Point(curR, curC)); // 시작 위치를 큐에 넣고
		arr[curR][curC] = 1; // 시작 위치를 방문 처리 (시작 위치를 다시 방문하지 못하도록 1로 설정했기 때문에 최소 이동 횟수 구할 때 1 빼야됨)
		
		while (!queue.isEmpty()) { // 큐가 빌 때 까지
			Point node = queue.poll(); // 큐에서 원소 하나를 꺼내고
			if (goalR == node.x && goalC == node.y) break; // 목표지점에 도달했다면 break
			for (int d = 0; d < 8; d++) {
				int nR = node.x + dR[d];
				int nC = node.y + dC[d];
				// 이동 가능한 칸 중에서 방문하지 않은 칸이 있다면
				if (0 <= nR && nR < N && 0 <= nC && nC < N && arr[nR][nC] == 0) {
					queue.add(new Point(nR, nC)); // 큐에 넣고
					arr[nR][nC] = arr[node.x][node.y] + 1; // 방문처리
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 체스판 한 변의 길이

			st = new StringTokenizer(br.readLine());
			int curR = Integer.parseInt(st.nextToken()); // 현재 행
			int curC = Integer.parseInt(st.nextToken()); // 현재 열

			st = new StringTokenizer(br.readLine());
			int goalR = Integer.parseInt(st.nextToken()); // 목표 행
			int goalC = Integer.parseInt(st.nextToken()); // 목표 열

			int[][] arr = new int[N][N]; // 체스판 2차원 배열
			
			bfs(arr, curR, curC, goalR, goalC, N);
			
			bw.write(arr[goalR][goalC] - 1 + "\n");
		}
		bw.close();
	}

}