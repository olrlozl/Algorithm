import java.io.*;
import java.util.*;

public class Main {
    public static boolean isPrime(int k) {
        if (k < 2) return false;

        boolean answer = true;

        for (int i = 2; i <= Math.sqrt(k); i++) {
            if (k % i == 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; i++) if (isPrime(Integer.parseInt(st.nextToken()))) cnt++;

        bw.write(cnt + "");
        bw.close();

    }
}