class Solution {
    public static int cnt = 0;
    
    public void recursive(int[] numbers, int target, int sum, int idx) {
        if (idx == numbers.length) {
            if (sum == target) cnt++;
            return;
        }
        recursive(numbers, target, sum + numbers[idx], idx + 1);
        recursive(numbers, target, sum - numbers[idx], idx + 1);
    }
    
    public int solution(int[] numbers, int target) {
        recursive(numbers, target, 0, 0);
        return cnt;
    }
}