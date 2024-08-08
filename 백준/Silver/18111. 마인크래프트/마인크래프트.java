import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int B = Integer.parseInt(st.nextToken()); // 초기 인벤토리 속 블록 수

        int[][] arr = new int[N][M];
        int min = 1000;
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int maxHeight = 0;
        int height = min;

        while (height <= max) {
            int B_copy = B;
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (height < arr[i][j]) {
                        time += (arr[i][j] - height) * 2;
                        B_copy += arr[i][j] - height;
                    } else if (height > arr[i][j]){
                        time += height - arr[i][j];
                        B_copy -= height - arr[i][j];;
                    }
                }
            }
            if (B_copy >= 0 && time <= minTime) {
                minTime = time;
                maxHeight = height;
            }
            height++;
        }

        bw.write(minTime + " " + maxHeight);
        bw.close();
    }
}