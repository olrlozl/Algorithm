import java.io.*;
import java.util.*;

public class Main {
    public static int result;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int len = (int) Math.pow(2, N);
        division(len, r, c);
        bw.write(result + "");
        bw.close();
    }

    public static void division(int len, int r, int c) {
        if (len == 1) return;

        if (r < len / 2) {
            if (c < len / 2) {
                division(len / 2, r, c);
            } else {
                result += len * len / 4;
                division(len / 2, r, c - len / 2);
            }
        } else {
            if (c < len / 2) {
                result += len * len / 2;
                division(len / 2, r - len / 2, c);
            } else {
                result += len * len / 4 * 3;
                division(len / 2, r - len / 2, c - len / 2);
            }
        }
    }
}