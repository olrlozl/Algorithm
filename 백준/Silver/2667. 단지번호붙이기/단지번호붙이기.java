import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	// 상 하 좌 우
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
			
	public static int bfs(int r, int c, int N, int[][] arr) {
		Queue<Point> queue = new LinkedList<>();
		int cnt = 0; // 현재 단지에 속하는 집의 수
		
		queue.add(new Point(r, c)); // 시작 위치를 큐에 넣고
		arr[r][c] = 0; // 시작 위치를 방문처리
		cnt++; // 현재 단지의 집의 수 늘리기
		
		while (!queue.isEmpty()) { // 큐가 빌 때까지
			Point home = queue.poll(); // 큐에서 원소 하나를 꺼내고
			for (int d = 0; d < 4; d++) {
				int nr = home.x + dr[d];
				int nc = home.y + dc[d];
				// 이웃한 원소 중에서 방문하지 않은 집이 있다면
				if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 1) {
					queue.add(new Point(nr, nc)); // 큐에 넣고
					arr[nr][nc] = 0; // 방문 처리
					cnt++; // 현재 단지의 집의 수 늘리기
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 지도 크기
		int[][] arr = new int[N][N]; // 지도
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		int total = 0; // 총 단지 수
		ArrayList<Integer> list = new ArrayList<>(); // 각 단지 내 집의 수
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] == 1) { // 집을 만나면
					list.add(bfs(r, c, N, arr)); // 해당 단지의 집의 수 추가하기
					total++; // 총 단지수 카운트
				}
			}
		}
		
		list.sort(Comparator.naturalOrder()); // 오름차순 정렬
		
		bw.write(total +"\n");
		for (int v : list) bw.write(v + "\n");
		
		bw.close();
	}

}
