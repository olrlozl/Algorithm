import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static ArrayList<Fireball>[][] board;
    public static int[][] delta = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            board[r][c].add(new Fireball(r, c, m, s, d));
        }


        for (int i = 0; i < K; i++) {
            move();
            division();
        }

        int sumM = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j].isEmpty()) {
                    for (Fireball ball: board[i][j]) {
                        sumM += ball.m;
                    }
                }
            }
        }

        bw.write(sumM + "");
        bw.close();
    }

    public static void move() {
        ArrayList<Fireball>[][] newBoard = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j].isEmpty()) {
                    for (Fireball ball: board[i][j]) {
                        int nr = (ball.r + (ball.s % N) * delta[ball.d][0] + N) % N;
                        int nc = (ball.c + (ball.s % N) * delta[ball.d][1] + N) % N;
                        ball.r = nr;
                        ball.c = nc;
                        newBoard[nr][nc].add(ball);
                    }
                }
            }
        }

        board = newBoard;
    }

    public static void division() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].size() >= 2) {
                    int sumM = 0, sumS = 0, cntEven = 0, cntOdd = 0;
                    int size = board[i][j].size();

                    for (Fireball ball : board[i][j]) {
                        sumM += ball.m;
                        sumS += ball.s;
                        if (ball.d % 2 == 0) cntEven++;
                        else cntOdd++;
                    }

                    board[i][j].clear();

                    if (sumM / 5 == 0) continue;

                    if (cntEven == size || cntOdd == size) {
                        for (int k = 0; k < 8; k += 2) {
                            board[i][j].add(new Fireball(i, j, sumM / 5, sumS / size, k));
                        }
                    } else {
                        for (int k = 1; k < 8; k += 2) {
                            board[i][j].add(new Fireball(i, j, sumM / 5, sumS / size, k));
                        }
                    }
                }
            }
        }
    }

    public static class Fireball{
        int r;
        int c;
        int m;
        int s;
        int d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}