import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] board;
    public static int maxN = 2;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxN = Math.max(maxN, board[i][j]);
            }
        }

        move(0);

        bw.write( maxN + "");
        bw.close();
    }

    public static void move(int cnt) {
        if (cnt == 5) {
            return;
        }

        int[][] tmp = copy(board);

        left(cnt);
        board = copy(tmp);

        right(cnt);
        board = copy(tmp);

        up(cnt);
        board = copy(tmp);

        down(cnt);
        board = copy(tmp);
    }

    public static int[][] copy(int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }

    public static void left(int cnt) {
        int[][] newBoard = new int[N][N];
        for (int r = 0; r < N; r++) {
            int nc = 0;
            int tmp = 0;
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0) continue;
                if (tmp == 0) {
                    tmp = board[r][c];
                } else if (tmp == board[r][c]) {
                    newBoard[r][nc++] = tmp * 2;
                    maxN = Math.max(maxN, tmp * 2);
                    tmp = 0;
                } else {
                    newBoard[r][nc++] = tmp;
                    tmp = board[r][c];
                }
            }
            newBoard[r][nc++] = tmp;
        }
        board = newBoard;

        move(cnt + 1);
    }

    public static void right(int cnt) {
        int[][] newBoard = new int[N][N];
        for (int r = N - 1; r >= 0; r--) {
            int nc = N - 1;
            int tmp = 0;
            for (int c = N - 1; c >= 0; c--) {
                if (board[r][c] == 0) continue;
                if (tmp == 0) {
                    tmp = board[r][c];
                } else if (tmp == board[r][c]) {
                    newBoard[r][nc--] = tmp * 2;
                    maxN = Math.max(maxN, tmp * 2);
                    tmp = 0;
                } else {
                    newBoard[r][nc--] = tmp;
                    tmp = board[r][c];
                }
            }
            newBoard[r][nc--] = tmp;
        }
        board = newBoard;

        move(cnt + 1);
    }

    public static void up(int cnt) {
        int[][] newBoard = new int[N][N];
        for (int c = 0; c < N; c++) {
            int nr = 0;
            int tmp = 0;
            for (int r = 0; r < N; r++) {
                if (board[r][c] == 0) continue;
                if (tmp == 0) {
                    tmp = board[r][c];
                } else if (tmp == board[r][c]) {
                    newBoard[nr++][c] = tmp * 2;
                    maxN = Math.max(maxN, tmp * 2);
                    tmp = 0;
                } else {
                    newBoard[nr++][c] = tmp;
                    tmp = board[r][c];
                }
            }
            newBoard[nr++][c] = tmp;
        }
        board = newBoard;

        move(cnt + 1);
    }

    public static void down(int cnt) {
        int[][] newBoard = new int[N][N];
        for (int c = N - 1; c >= 0; c--) {
            int nr = N - 1;
            int tmp = 0;
            for (int r = N - 1; r >= 0; r--) {
                if (board[r][c] == 0) continue;
                if (tmp == 0) {
                    tmp = board[r][c];
                } else if (tmp == board[r][c]) {
                    newBoard[nr--][c] = tmp * 2;
                    maxN = Math.max(maxN, tmp * 2);
                    tmp = 0;
                } else {
                    newBoard[nr--][c] = tmp;
                    tmp = board[r][c];
                }
            }
            newBoard[nr--][c] = tmp;
        }
        board = newBoard;

        move(cnt + 1);
    }
}