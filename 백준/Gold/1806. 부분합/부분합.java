import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int sum = 0;

        while (r < N) {
            sum += arr[r++];

            while (sum >= S) {
                minLen = Math.min(minLen, r - l);
                sum -= arr[l++];
            }
        }

        bw.write((minLen == Integer.MAX_VALUE ? 0 : minLen) + "");
        bw.close();
    }
}