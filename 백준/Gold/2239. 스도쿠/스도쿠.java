import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    static int[][] arr = new int[9][9];
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][] box = new boolean[9][10];
    static ArrayList<Point> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                int n = line.charAt(j) - '0';
                arr[i][j] = n;

                if (n == 0){
                    blanks.add(new Point(i, j));
                } else {
                    row[i][n] = true;
                    col[j][n] = true;
                    box[(i / 3) * 3 + (j / 3)][n] = true;
                }
            }
        }

        dfs(0);
    }

    public static void dfs(int idx) {
        if (idx == blanks.size()) {
            for (int i = 0; i < 9; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]);
                }
                System.out.println(sb);
            }
            System.exit(0);
        }

        int r = blanks.get(idx).x;
        int c = blanks.get(idx).y;
        int b = (r / 3) * 3 + (c / 3);

        if (arr[r][c] > 0) {
            dfs(idx + 1);
            return;
        }

        for (int n = 1; n <= 9; n++) {
            if (!row[r][n] && !col[c][n] && !box[b][n]) {
                row[r][n] = col[c][n] = box[b][n] = true;
                arr[r][c] = n;

                dfs(idx + 1);

                row[r][n] = col[c][n] = box[b][n] = false;
                arr[r][c] = 0;
            }
        }
    }
}