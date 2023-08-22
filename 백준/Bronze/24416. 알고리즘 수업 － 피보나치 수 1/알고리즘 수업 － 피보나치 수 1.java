import java.util.Scanner;

public class Main {
    public static int cnt1 = 0;
    public static int cnt2 = 0;

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            cnt1++;
            return 1;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 재귀 호출
        fib(n);

        // 동적 프로그래밍
        int[] f = new int[n+1];
        f[1] = 1;
        f[2] = 1;
        for (int i = 3; i < n+1; i++) {
            cnt2++;
            f[i] = f[i-1] + f[i-2];
        }

        System.out.println(cnt1 + " " + cnt2);
    }
}