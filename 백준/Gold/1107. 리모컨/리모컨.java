import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 타겟 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수
        boolean[] broken = new boolean[10]; // 정상(F) 고장(T)
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int cnt = Math.abs(100 - N); // 현재 채널 100에서 타겟 채널 N까지 +- 버튼으로만 이동하는 경우

        // 현재 채널 100에서 숫자 버튼으로 i로 이동 후에, 타겟 채널 N까지 +- 버튼으로 이동하는 경우
        for (int i = 0; i <= 999999; i++) {
            String num = Integer.toString(i);
            boolean isbroken = false;
            for (char c : num.toCharArray()) {
                if (broken[c - '0']) {
                    isbroken = true;
                    break;
                }
            }
            if (!isbroken) { // 숫자 버튼으로 i로 이동할 수 있는 경우에만
                cnt = Math.min(cnt, num.length() + Math.abs(N - i));
            }
        }

        bw.write(cnt + "");
        bw.close();
    }
}