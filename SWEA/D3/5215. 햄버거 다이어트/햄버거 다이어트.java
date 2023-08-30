import java.io.*;
import java.util.*;

public class Solution {
	
	public static int result; // 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수
	
	public static void subset (int i, int N, int L, int[] score, int[] kcal, boolean[] select) {
		if (i == N) {
			// 총 칼로리 구하기
			int total_kcal = 0;
			for (int j = 0; j < N; j++) {
				if (select[j]) {
					total_kcal += kcal[j];
				}
			}
			// 제한 칼로리 이하의 조합일 경우, 총 맛 점수 구하기
			int total_score = 0;
			if (total_kcal <= L) {
				for (int j = 0; j < N; j++) {
					if (select[j]) {
						total_score += score[j];
					}
				}
				result = Math.max(result, total_score);
			}
			return;
		}
		
		select[i] = false; // i번째 재료 포함 X
		subset(i+1, N, L, score, kcal, select); // i+1번째 재료 포함 여부 결정하기 위해 재귀 호출
		select[i] = true; // i번째 재료 포함 O
		subset(i+1, N, L, score, kcal, select); // i+1번째 재료 포함 여부 결정하기 위해 재귀 호출
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 재료의 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			int[] score = new int[N]; // 맛 점수
			int[] kcal = new int[N]; // 칼로리
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}
			
			boolean[] select = new boolean[N]; // 재료 선택 여부
			result = 0; // 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수
			
			subset(0, N, L, score, kcal, select);
			
			bw.write("#" + t + " " + result + " \n");
		}
		
		bw.close();
	}

}