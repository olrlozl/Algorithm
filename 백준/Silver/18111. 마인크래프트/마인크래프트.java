import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int min = Integer.MAX_VALUE;
        int max = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;

        for (int h = min; h <= max; h++) {
            int b = B;
            int t = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] < h) {
                        t += h - arr[i][j];
                        b -= h - arr[i][j];
                    } else if (arr[i][j] > h) {
                        t += 2 * (arr[i][j] - h);
                        b += arr[i][j] - h;
                    }
                }
            }

            if (b < 0) continue;

            if (t < minTime || (t == minTime && height < h)) {
                minTime = t;
                height = h;
            }
        }

        System.out.println(minTime + " " + height);
    }
}