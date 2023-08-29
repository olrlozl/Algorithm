import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로

		int[][] arr = new int[N][M]; // 미로
		
		for (int n = 0; n < N; n++) {
			char[] row = br.readLine().toCharArray();
			
			for (int m = 0; m < M; m++) {
				arr[n][m] = row[m] - '0';
			}
		}

		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
		Queue<Point> queue = new LinkedList<>(); // 큐
		
		int num = 1;
		queue.add(new Point(0, 0)); // 시작 위치를 큐에 넣고
		
		while (!queue.isEmpty()) { // 큐가 빌 때 까지
			Point road = queue.poll(); // 큐에서 원소 하나를 빼내고
			
			for (int d = 0; d < 4; d++) { // 이웃한 길 중에서
				int nr = road.x + delta[d][0];
				int nc = road.y + delta[d][1];
				
				// 방문하지 않은 길이 있다면 (범위를 벗어나지 않고, 시작위치가 아니여야함)
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !(nr == 0 && nc == 0) && arr[nr][nc] == 1) {
					queue.add(new Point(nr, nc)); // 큐에 넣고
					arr[nr][nc] = arr[road.x][road.y] + 1; // 방문처리
				}
			}
		}

		bw.write(arr[N-1][M-1] + ""); // (N,M)위치 값
		bw.close();
	}

}