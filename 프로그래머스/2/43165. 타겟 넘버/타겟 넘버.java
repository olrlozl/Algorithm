class Solution {
    public int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        recursive(numbers, target, 0, 0);
        return cnt;
    }
    
    public void recursive(int[] numbers, int target, int sum, int idx) {
        if (numbers.length == idx) {
            if (sum == target) {
                cnt++;
            }
            return;
        }
        recursive(numbers, target, sum + numbers[idx], idx + 1);
        recursive(numbers, target, sum - numbers[idx], idx + 1);
    }
}