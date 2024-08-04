import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 에라토스테네스의 체
        boolean[] arr = new boolean[N + 1]; // 소수(F) 소수아님(T)
        arr[0] = true;
        arr[1] = true;
        
        for (int i = 2; i <= (int) Math.sqrt(N); i++) {
            if (arr[i]) continue;
            for (int j = i * i; j <= N; j += i) { // i * i 부터 확인하면 됨
                arr[j] = true; // i의 배수들 소수아님
            }
        }

        for (int i = M; i <= N; i++) {
            if (!arr[i]) bw.write(i + "\n");
        }
        bw.close();
    }
}