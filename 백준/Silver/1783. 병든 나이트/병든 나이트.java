import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int count = 0;

        if (N == 1) { // 이동 불가능
            count = 1;
        } else if (N == 2) { // 2, 3번 방향 반복해서 이동 (네 가지 방향 모두 이동 불가능하므로 이동횟수 4가 최대)
            count = Math.min((M + 1) / 2, 4);
        } else {
            if (M < 7) {  // 1, 4번 방향 반복하는게 최선 (네 가지 방향 모두 이동 불가능하므로 이동횟수 4가 최대)
                count = Math.min(M, 4);
            } else {  // 가로 7이면 네가지 방향 모두 이동 가능. 그 후 1,4번 방향 반복
                count = M - 2;
            }
        }

        bw.write(count + "");
        bw.close();
    }
}