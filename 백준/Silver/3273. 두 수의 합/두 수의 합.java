import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int cnt = 0;
        int l = 0;
        int r = N - 1;

        while (l < r) {
            if (arr[l] + arr[r] == K) {
                cnt++;
                l++;
                r--;
            } else if (arr[l] + arr[r] < K) {
                l++;
            } else {
                r--;
            }
        }

        bw.write(cnt + "");
        bw.close();
    }
}