class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(j == 0 ? 0 : dp[i - 1][j - 1], j == i ? 0 : dp[i - 1][j]);
            }
        }
        
        int max = 0;
        for (int i = 0; i < triangle.length; i++) {
            max = Math.max(max, dp[triangle.length - 1][i]);
        }
        return max;
    }
}