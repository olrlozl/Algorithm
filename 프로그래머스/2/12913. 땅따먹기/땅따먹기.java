import java.util.*;

class Solution {
    int solution(int[][] land) {
        for (int i = 1; i < land.length; i++) { // 행
            for (int j = 0; j < 4; j++) { // 열
                int max = 0;
                for (int k = 0; k < 4; k++) { // 이전 행의 열
                    if (j != k) {
                        max = Math.max(max, land[i - 1][k]);
                    } 
                }
                land[i][j] += max;
            }
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = Math.max(result, land[land.length - 1][i]);
        }
        
        return result;
    }
}