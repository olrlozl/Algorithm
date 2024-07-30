import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long T = Long.parseLong(br.readLine());
        
        long[] arr = new long[101];
        
    	arr[1] = 1;
    	arr[2] = 1;
    	arr[3] = 1;
    	arr[4] = 2;
    	arr[5] = 2;
    	
    	for (int i = 6, j = 1; i < 101; i++, j++) arr[i] = arr[j] + arr[i-1];
    	
        for (int t = 1; t <= T; t++) {
        	int N = Integer.parseInt(br.readLine());
        	bw.write(arr[N] + "\n");
        }

        bw.close();
    }
}