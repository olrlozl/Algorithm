import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for (int r = 1; r < triangle.length; r++) {
            for (int c = 0; c < triangle[r].length; c++) {
                if (c < 1) triangle[r][c] += triangle[r - 1][c];
                else if (c == r) triangle[r][c] += triangle[r - 1][c - 1];
                else triangle[r][c] += Math.max(triangle[r - 1][c - 1], triangle[r - 1][c]);
                if (r == triangle.length - 1) answer = Math.max(answer, triangle[r][c]);
            }
        }
        return answer;
    }
}