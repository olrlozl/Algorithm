import java.util.*;

class Solution {
    public int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] arr = new int[rows][columns];
        for (int a = 1, i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = a++;
            }
        }

        for (int q = 0; q < queries.length; q++) {
            int r = queries[q][0] - 1;
            int c = queries[q][1] - 1;
            int tmp = arr[r][c];
            int min = arr[r][c];
            int turn = 0;

            while (true) {
                int nr = r + delta[turn][0];
                int nc = c + delta[turn][1];

                if (nr < queries[q][0] - 1 || nr > queries[q][2] - 1 || nc < queries[q][1] - 1 || nc > queries[q][3] - 1) {
                    turn++;
                    continue;
                }

                min = Math.min(min, arr[nr][nc]);

                if (nr == queries[q][0] - 1 && nc == queries[q][1] - 1) {
                    arr[r][c] = tmp;
                    break;
                }

                arr[r][c] = arr[nr][nc];
                r = nr;
                c = nc;
            }

            answer[q] = min;
        }
        
        return answer;
    }
}