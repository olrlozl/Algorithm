import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
	// 상 하 좌 우
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void bfs(int r, int c, int[][] arr) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c)); // 시작 위치를 큐에 넣고
		arr[r][c] = 0; // 시작 위치를 방문처리

		while (!queue.isEmpty()) { // 큐가 빌 때까지
			Point node = queue.poll(); // 큐에서 원소 하나를 꺼내고
			for (int d = 0; d < 4; d++) {
				int nr = node.x + dr[d];
				int nc = node.y + dc[d];
				// 이웃한 원소 중에 방문하지 않은 원소가 있다면
				if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length && arr[nr][nc] == 1 ) {
					queue.add(new Point(nr, nc)); // 큐에 넣고
					arr[nr][nc] = 0; // 방문 처리
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
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 가로 길이
			int N = Integer.parseInt(st.nextToken()); // 세로 길이
			int K = Integer.parseInt(st.nextToken()); // 배추 개수

			int[][] arr = new int[N][M]; // 배추밭

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[y][x] = 1;
			}

			int result = 0; // 필요한 최소의 배추흰지렁이 마리 수

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (arr[r][c] == 1) { // 배추가 있다면
						bfs(r, c, arr);
						result++;
					}
				}
			}
			bw.write(result + "\n");
		}
		bw.close();
	}

}