class Solution {
    public int cnt;
    
    public int solution(int[] numbers, int target) {
        func(numbers, target, 0, 0);
        return cnt;
    }
    
    public void func(int[] numbers, int target, int res, int idx) {
        if (idx == numbers.length) {
            if (res == target) cnt++;
            return;
        }
        func(numbers, target, res + numbers[idx], idx + 1);
        func(numbers, target, res - numbers[idx], idx + 1);
    }
}