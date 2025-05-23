import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {       
        int[][] arr = new int[n + 1][n + 1];
        
        for (int[] r: results) {
            arr[r[0]][r[1]] = 1;
            arr[r[1]][r[0]] = -1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    } else if (arr[i][k] == -1 && arr[k][j] == -1) {
                        arr[i][j] = -1;
                        arr[j][i] = 1;
                    } 
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == 0) cnt++;
                if (cnt > 1) break;
            }
            if (cnt == 1) answer++;
        }
        
        return answer;
    }
}