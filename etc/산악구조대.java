import java.awt.*;
import java.io.*;
import java.util.*;

public class Solution {
	public static int n;
	public static int[][] arr;
	public static int[][] delta = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	public static int minOil;
	
	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		int[][] visit = new int[n][n];
		for (int i = 0; i < n; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
		
		queue.add(new Point(0, 0));
		visit[0][0] = 0;
		
		while (!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				Point now = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = now.x + delta[d][0];
					int nc = now.y + delta[d][1];
					
					if (0 > nr || nr >= n || 0 > nc || nc >= n) continue;
					
					int need = 0;
					if (arr[now.x][now.y] < arr[nr][nc])
						need = 2 * (arr[nr][nc] - arr[now.x][now.y]);
					else if (arr[now.x][now.y] == arr[nr][nc])
						need = 1;
					
					if (visit[now.x][now.y] + need < visit[nr][nc]) {
						queue.add(new Point(nr, nc));
						visit[nr][nc] = visit[now.x][now.y] + need;
					}
				}
			}
		}
		
		minOil = Math.min(minOil, visit[n-1][n-1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minOil = Integer.MAX_VALUE;
			bfs();
			
			bw.write("#" + t + " " + minOil + "\n");
		}

		bw.close();

	}

}
