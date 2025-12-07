import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int[][] delta = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] cloud = new boolean[N][N];
        cloud[N-2][0] = true;
        cloud[N-2][1] = true;
        cloud[N-1][0] = true;
        cloud[N-1][1] = true;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            boolean[][] temp = new boolean[N][N];

            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    if (cloud[a][b]) {
                        int dx = ((a + delta[d - 1][0] * s) % N + N) % N;
                        int dy = ((b + delta[d - 1][1] * s) % N + N) % N;
                        temp[dx][dy] = true;
                        arr[dx][dy] += 1;
                    }
                }
            }

            cloud = temp;

            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    if (cloud[a][b]) {
                        for (int j = 1; j < 8; j+=2) {
                            int dx = a + delta[j][0];
                            int dy = b + delta[j][1];
                            if (0 <= dx && dx < N && 0 <= dy && dy < N && arr[dx][dy] > 0) {
                                arr[a][b]++;
                            }
                        }
                    }
                }
            }

            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    if (cloud[a][b]) {
                        cloud[a][b] = false;
                    } else if (arr[a][b] >= 2) {
                        cloud[a][b] = true;
                        arr[a][b] -= 2;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += arr[i][j];
            }
        }

        bw.write(result + "");
        bw.close();
    }
}