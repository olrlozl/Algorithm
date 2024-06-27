import java.io.*;

public class Main {
    public static boolean bingo(Character[][] board, char stone) {
        for (int i = 0; i < 3; i++) {
            // 가로 확인
            if (stone == board[i][0] && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            // 세로 확인
            if (stone == board[0][i] && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        // 대각선 확인
        if (stone == board[0][0] && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (stone == board[0][2] && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = br.readLine();
            if (s.equals("end")) break;

            Character[][] board = new Character[3][3];
            int idx = 0;
            int turn = 0;
            boolean full = true;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = s.charAt(idx++);
                    if (board[i][j] == 'X') {
                        turn++;
                    } else if (board[i][j] == 'O') {
                        turn--;
                    } else if (board[i][j] == '.') {
                        full = false;
                    }
                }
            }

            boolean X_bingo = bingo(board, 'X');
            boolean O_bingo = bingo(board, 'O');

            // 게임 규칙에서 벗어난 경우, 게임이 안끝난 경우 invalid
            if (turn > 1 || turn < 0 || (turn == 1 && O_bingo) || (turn == 0 && X_bingo)
                    || (!full & !X_bingo && !O_bingo)) {
                bw.write("invalid\n");
            } else {
                bw.write("valid\n");
            }
        }
        bw.close();
    }
}