class Solution {
    public int[] solution(int[] sequence, int k) {
        int N = sequence.length;
        int[] answer = {0, N - 1};
        int sum = sequence[0], l = 0, r = 0;
        
        while (l <= r && r < N) {
            if (sum == k) {
                if (answer[1] - answer[0] > r - l) {
                    answer[0] = l;
                    answer[1] = r;
                }
                if (r == N - 1) break;
                sum += sequence[++r] - sequence[l++];
            } else if (sum < k) {
                if (r == N - 1) break;
                sum += sequence[++r];
            } else {
                sum -= sequence[l++];
            }
        }
        
        return answer;
    }
}