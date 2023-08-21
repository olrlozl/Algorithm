import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i ++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int cnt = 0;
		long right = arr[N-1];
		long left = 1;
		long result = 0;
		
		while (left <= right) {
			long mid = (right + left) / 2;
			
			cnt = 0;
			for (int a : arr) cnt += a / mid;
			
			if (cnt < K) 
				right = mid - 1;
			else {
				result = Math.max(result, mid);
				left = mid + 1;
			}
		} 
		
		bw.write(result + "");
		bw.close();
	}

}