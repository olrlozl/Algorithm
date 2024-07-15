import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 국가 수
        int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가
        int[][] arr = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 국가 번호
            arr[i][1] = Integer.parseInt(st.nextToken()); // 금메달 수
            arr[i][2] = Integer.parseInt(st.nextToken()); // 은메달 수
            arr[i][3] = Integer.parseInt(st.nextToken()); // 동메달 수
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                if (a[2] == b[2]) {
                    return b[3] - a[3];
                } else {
                    return b[2] - a[2];
                }
            } else {
                return b[1] - a[1];
            }
        });

        int rank = 0;
        int g = -1;
        int s = -1;
        int b = -1;

        for (int i = 0; i < N; i++) {
            if (g != arr[i][1] || s != arr[i][2] || b != arr[i][3]) rank = i + 1;
            if (arr[i][0] == K) break;
            g = arr[i][1];
            s = arr[i][2];
            b = arr[i][3];
        }

        bw.write(rank + "");
        bw.close();
    }
}