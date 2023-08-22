import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람 수
        int[] arr = new int[N]; // 사람별 인출 시 필요한 시간
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        for (int i = 1; i < N; i++) arr[i] += arr[i-1];
        int result = 0;
        for (int i = 0; i < N; i++) result += arr[i];
        System.out.println(result);
    }
}