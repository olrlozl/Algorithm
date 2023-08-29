import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// N자리수만큼 1로 채우기
			int n = 0;
			for (int i = 0; i < N; i++) n = n * 2 + 1;
			
			if ((M & n) == n) bw.write("#" + t + " ON\n");
			else bw.write("#" + t + " OFF\n");
		}
		
		bw.close();		
	}
	
}