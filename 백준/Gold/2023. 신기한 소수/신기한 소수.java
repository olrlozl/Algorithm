import java.io.*;

public class Main {
    public static int N;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        func(0, 0);

        bw.close();
    }

    public static void func(int cur, int depth) throws IOException {
        if (depth == N) {
            bw.write(cur + "\n");
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (isPrime(cur * 10 + i)) {
                func(cur * 10 + i, depth + 1);
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}