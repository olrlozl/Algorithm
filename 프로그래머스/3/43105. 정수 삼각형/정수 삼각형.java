import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(j == 0 ? 0 : triangle[i - 1][j - 1], j == i ? 0 : triangle[i - 1][j]);
            }
        }
        
        int max = 0;
        for (int n : triangle[triangle.length - 1]) max = Math.max(max, n);
        return max;
    }
}