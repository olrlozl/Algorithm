class Solution {
    public int solution(int n) {
        int cnt = 1;

        for (int i = 1; i <= n/2; i++) {
            int sum = 0;
            for (int j = i; j <= n/2+1; j++) {
                sum += j;
                if (sum == n) {
                    cnt++;
                    break;
                }
                if (sum > n) break;
            }
        }
        return cnt;
    }
}