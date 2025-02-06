import java.io.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int M;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        hanoi_4v(N, 'A' , 'B', 'C', 'D');
        bw.write(M + "\n" + sb);
        bw.close();
    }

    public static void move(char from, char to) {
        sb.append(from).append(" ").append(to).append("\n");
        M++;
    }

    public static void hanoi(int N, char from, char mid, char to) {
        if (N == 1) {
            move(from , to);
            return;
        }
        hanoi(N - 1, from, to, mid);
        move(from , to);
        hanoi(N - 1, mid, from, to);
    }

    public static void hanoi_4v(int N, char from, char mid1, char mid2, char to) {
        if (N == 1) {
            move(from , to);
            return;
        }
        if (N == 2) {
            move(from , mid1);
            move(from , to);
            move(mid1 , to);
            return;
        }
        hanoi(N - 2, from, mid2, mid1);
        move(from , mid2);
        move(from , to);
        move(mid2 , to);
        hanoi_4v(N - 2, mid1, mid2, from, to);
    }
}