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

			// N자리수만큼 1로 채운 수 : (1 << N) - 1
			if ((M & ((1 << N) - 1)) == ((1 << N) - 1)) bw.write("#" + t + " ON\n");
			else bw.write("#" + t + " OFF\n");
            
		}
		bw.close();
	}

}