class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        loop: for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) {
                    continue loop;
                }
            }
        }
        
        return answer;
    }
}