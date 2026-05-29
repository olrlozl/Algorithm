class Solution {
    public int[] solution(int[] sequence, int k) {
        int N = sequence.length;
        int[] answer = {0, N - 1};
        int sum = 0;
        int l = 0;
        
        for (int r = 0; r < N; r++) {
            sum += sequence[r];
            
            while (sum > k) {
                sum -= sequence[l++];
            }
            
            if (sum == k && r - l < answer[1] - answer[0]) {
                answer[0] = l;
                answer[1] = r;
            }
        }
        
        return answer;
    }
}