import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 나무 수
        int M = Integer.parseInt(st.nextToken()); // 필요한 최소 나무 길이

        int[] arr = new int[N];
        int min = 0;
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int result = 0;

        while (min <= max) {
            int mid = (min + max) / 2;

            long sum = 0;
            for (int a : arr) {
                if (a - mid > 0) sum += a - mid;
                if (sum > M) break;
            }

            if (sum < M) {
                max = mid - 1;
            }
            else if (sum > M) {
                result = Math.max(result, mid);
                min = mid + 1;
            } else {
                result = mid;
                min = max + 1;
            }
        }

        bw.write(result + "");
        bw.close();
    }
}