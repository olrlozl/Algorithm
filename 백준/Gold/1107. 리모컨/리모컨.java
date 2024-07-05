import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] broken;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 타겟 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수
        broken = new boolean[10]; // 정상(F) 고장(T)
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        answer = Math.abs(N - 100);
        fn(0, 0);
        bw.write(answer + "");
        bw.close();
    }

    public static void fn(int depth, int cur) {
        if (depth > 0) {
            answer = Math.min(answer, depth + Math.abs(N - cur));
        }

        if (depth == 6) return;

        for (int i = 0; i <= 9; i++) {
            if (!broken[i]) {
                fn(depth + 1, cur * 10 + i);
            }
        }
    }
}