class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] result = {0, sequence.length};
        
        int sum = sequence[0];
        int l = 0;
        int r = 0;
        
        while (l <= r && r < sequence.length) {
            if (sum == k) {
                if (result[1] - result[0] > r - l) {
                    result[0] = l;
                    result[1] = r;
                }
                sum -= sequence[l++];
            } 
            else if (sum < k) {
                if (r == sequence.length - 1) break;
                sum += sequence[++r];
            } 
            else {
                sum -= sequence[l++];
            }
        }
        
        return result;
    }
}