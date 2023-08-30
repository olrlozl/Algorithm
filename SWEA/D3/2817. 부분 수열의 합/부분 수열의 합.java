import java.io.*;
import java.util.*;

public class Solution {

	public static int N; // 자연수 개수
	public static int K; // 합
	public static int[] arr; // 자연수 수열
	public static boolean[] select; // 포함 여부 담을 배열
	public static int result; // 부분 수열의 합이 K가 되는 경우의 수
	
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void subset (int i) throws Exception {
		if (i == N) { // N개 요소의 포함여부를 모두 결정했다면
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if (select[j]) { // 포함되는 요소만
					sum += arr[j]; // 합산
				}
			}
			if (sum == K) result++; // 부분수열의 합이 K가 된다면
			return;
		}
		select[i] = false; // i번째 요소 포함 X
		subset(i+1); // i+1번째 요소 포함 여부 결정하기 위해 재귀 호출
		
		select[i] = true; // i번째 요소 포함 O
		subset(i+1); // i+1번째 요소 포함 여부 결정하기 위해 재귀 호출
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 자연수 개수
			K = Integer.parseInt(st.nextToken()); // 합
			
			arr = new int[N]; // 자연수 수열
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) arr[n] = Integer.parseInt(st.nextToken());
			
			select = new boolean[N]; // 포함 여부 담을 배열
			result = 0; // 부분 수열의 합이 K가 되는 경우의 수
			
			subset(0);
			
			bw.write("#" + t + " " + result + "\n");
		}
		bw.close();
	}

}