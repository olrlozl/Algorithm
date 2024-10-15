import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Long> list = new ArrayList<>(); // 모든 감소하는 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 9; i++) func(i, 1);
        Collections.sort(list);

        if (N > list.size() - 1) bw.write("-1");
        else bw.write(list.get(N) + "");

        bw.close();
    }

    public static void func(long num, int idx) { // 현재수, 자릿수
        if (idx > 10) return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            func(num * 10 + i, idx + 1);
        }
    }
}