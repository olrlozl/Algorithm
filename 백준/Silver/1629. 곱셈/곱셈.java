import java.io.*;
import java.util.*;

public class Main {
    public static long c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bw.write(pow(a, b) + "");
        bw.close();
    }

    // 모듈러 합동 공식: (a * b) % c == ((a % c) * (b % c)) % c
    public static long pow(long a, long b) {
        if (b == 1) return a % c;
        long tmp = pow(a, b / 2);
        if (b % 2 == 0) return tmp * tmp % c;
        return ((tmp * tmp % c) * (a % c)) % c; // tmp * tmp * a % c;
    }
}