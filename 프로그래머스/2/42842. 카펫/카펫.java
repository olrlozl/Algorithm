class Solution {
    public int[] solution(int brown, int yellow) {
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0 && brown == (i+2) * (yellow/i+2) - yellow) {
                return new int[] {yellow/i + 2, i + 2};
            }
        }
        return null;
    }
}

// yellow = x * y
// brown = (x+2) (y+2) - yellow