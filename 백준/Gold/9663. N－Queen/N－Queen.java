import java.io.*;

public class Main {
    public static int N;
    public static int[] arr;
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        simulate(0);
        bw.write(cnt + "");
        bw.close();
    }

    // r행 에서 퀸을 놓을 열 정하기
    public static void simulate(int r) {
        if (r == N) {
            cnt++;
            return;
        }
        for (int c = 0; c < N; c++) {
            if (isPossible(r, c)) {
                arr[r] = c;
                simulate(r + 1);
            }
        }
    }
    // r행 c열에 퀸을 놓을 수 있는지 확인
    public static boolean isPossible(int r, int c) {
        for (int pr = 0; pr < r; pr++) {
            if (c == arr[pr] || Math.abs(c - arr[pr]) == r - pr) {
                return false;
            }
        }
        return true;
    }
}