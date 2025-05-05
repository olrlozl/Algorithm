import java.io.*;
import java.util.*;

public class Main {
    public static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();

        for (int num : arr) {
            int idx = binarySearch(num);
            if (idx < 0) idx = - idx- 1;

            if (idx == list.size()) list.add(num);
            else list.set(idx, num);
        }

        bw.write(list.size() + "");
        bw.close();
    }

    public static int binarySearch(int num) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (num <= list.get(mid)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}